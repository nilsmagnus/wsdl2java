package no.nils.wsdl2java

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSet

class Wsdl2JavaPlugin implements Plugin<Project> {
    public static final String WSDL2JAVA = "wsdl2java"
    public static final String CLEAN = "deleteGeneratedSources"

    public static final DEFAULT_DESTINATION_DIR = "build/generatedsources/src/main/java"

    private static final JAVA_9_DEPENDENCIES = [
            'com.sun.xml.bind:jaxb-xjc:2.3.0.1',
            'javax.xml.bind:jaxb-api:2.3.1',
            'javax.xml.ws:jaxws-api:2.3.1',
            'org.apache.cxf:cxf-rt-wsdl:3.2.7',
            'javax.jws:javax.jws-api:1.1'
    ]

    void apply(Project project) {
        project.apply(plugin: 'java')

        def extension = project.extensions.create(WSDL2JAVA, Wsdl2JavaPluginExtension.class)
        def cxfVersion = extension.cxfVersion

        // Add new configuration for our plugin and add required dependencies to it.
        def wsdl2javaConfiguration = project.configurations.maybeCreate(WSDL2JAVA).tap {
            it.defaultDependencies {
                it.add(project.dependencies.create("org.apache.cxf:cxf-tools-wsdlto-databinding-jaxb:$cxfVersion"))
                it.add(project.dependencies.create("org.apache.cxf:cxf-tools-wsdlto-frontend-jaxws:$cxfVersion"))
                it.add(project.dependencies.create("org.apache.cxf.xjcplugins:cxf-xjc-ts:$cxfVersion"))
                it.add(project.dependencies.create("org.apache.cxf.xjcplugins:cxf-xjc-boolean:$cxfVersion"))

                if (JavaVersion.current().isJava9Compatible()) {
                    JAVA_9_DEPENDENCIES.each { dep -> it.add(project.dependencies.create(dep)) }
                }
            }
        }

        // Get compile configuration and add Java 9+ dependencies if required.
        project.configurations.named("compile").configure {
            it.defaultDependencies {
                if (JavaVersion.current().isJava9Compatible()) {
                    JAVA_9_DEPENDENCIES.each { dep -> it.add(project.dependencies.create(dep)) }
                }
            }
        }

        def wsdl2JavaTask = project.tasks.register(WSDL2JAVA, Wsdl2JavaTask.class) { task ->
            task.group = 'Wsdl2Java'
            task.description = 'Generate java source code from WSDL files.'
            task.classpath = wsdl2javaConfiguration
            task.extension = extension
        }

        def cleanWsdlTask = project.tasks.register(CLEAN, WsdlCleanTask) { task ->
            task.group = 'Wsdl2Java'
            task.description = 'Delete java source code generated from WSDL and XSD files.'
        }

        if (extension.deleteGeneratedSourcesOnClean) {
            project.tasks.named("clean").configure {
                dependsOn cleanWsdlTask
            }
        }

        project.tasks.named("compileJava").configure {
            dependsOn wsdl2JavaTask
        }

        project.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
            project.tasks.named("compileKotlin").configure {
                dependsOn wsdl2JavaTask
            }
        }

        project.pluginManager.withPlugin("org.jetbrains.kotlin.kapt") {
            project.afterEvaluate {
                project.tasks.named("kaptKotlin").configure {
                    dependsOn wsdl2JavaTask
                }
            }
        }

        project.afterEvaluate {
            def convention = project.getConvention().getPlugin(JavaPluginConvention)
            def mainSourceSet = convention.sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME)

            mainSourceSet.java.srcDir extension.generatedWsdlDir
        }
    }
}
