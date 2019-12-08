package no.nils.wsdl2java

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class Wsdl2JavaPlugin implements Plugin<Project> {
    public static final String WSDL2JAVA = "wsdl2java"

    private static final JAVA_9_DEPENDENCIES = [
            "javax.xml.bind:jaxb-api:2.3.1",
            "javax.xml.ws:jaxws-api:2.3.1",
            "org.glassfish.jaxb:jaxb-runtime:2.3.2",
            "org.glassfish.main.javaee-api:javax.jws:3.1.2.2",
            "com.sun.xml.messaging.saaj:saaj-impl:1.5.1"
    ]

    void apply(Project project) {
        project.apply(plugin: "java")

        def extension = project.extensions.create(WSDL2JAVA, Wsdl2JavaPluginExtension.class)
        def cxfVersion = project.provider { extension.cxfVersion }

        // Get compile configuration and add Java 9+ dependencies if required.
        project.configurations.named("compile").configure {
            it.defaultDependencies {
                if (JavaVersion.current().isJava9Compatible()) {
                    JAVA_9_DEPENDENCIES.each { dep -> it.add(project.dependencies.create(dep)) }
                }
            }
        }

        def wsdl2JavaTask = project.tasks.register(WSDL2JAVA, Wsdl2JavaTask.class) { task ->
            // Add new configuration for our plugin and add required dependencies to it.
            def wsdl2javaConfiguration = project.configurations.maybeCreate(WSDL2JAVA).tap {
                it.defaultDependencies {
                    it.add(project.dependencies.create("org.apache.cxf:cxf-tools-wsdlto-databinding-jaxb:${cxfVersion.get()}"))
                    it.add(project.dependencies.create("org.apache.cxf:cxf-tools-wsdlto-frontend-jaxws:${cxfVersion.get()}"))
                    it.add(project.dependencies.create("org.apache.cxf.xjcplugins:cxf-xjc-ts:${cxfVersion.get()}"))
                    it.add(project.dependencies.create("org.apache.cxf.xjcplugins:cxf-xjc-boolean:${cxfVersion.get()}"))

                    if (JavaVersion.current().isJava9Compatible()) {
                        JAVA_9_DEPENDENCIES.each { dep -> it.add(project.dependencies.create(dep)) }
                    }
                }
            }

            task.group = "Wsdl2Java"
            task.description = "Generate java source code from WSDL files."
            task.classpath = wsdl2javaConfiguration
            task.extension = extension
        }

        project.tasks.named("compileJava").configure {
            it.dependsOn wsdl2JavaTask
        }

        project.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
            project.tasks.withType(getTaskClass("org.jetbrains.kotlin.gradle.tasks.KotlinCompile")).configureEach {
                it.dependsOn wsdl2JavaTask
            }
        }

        project.pluginManager.withPlugin("org.jetbrains.kotlin.kapt") {
            project.tasks.withType(getTaskClass("org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask")).configureEach {
                it.dependsOn wsdl2JavaTask
            }
        }

        project.sourceSets {
            main.java.srcDirs += Wsdl2JavaTask.DESTINATION_DIR
        }
    }

    static Class<Task> getTaskClass(name) {
        return Class.forName(name) as Class<Task>
    }
}
