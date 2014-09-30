package no.nils.wsdl2java

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration

class Wsdl2JavaPlugin implements Plugin<Project> {
    public static final String WSDL2JAVA = "wsdl2java"
    public static final String XSD2JAVA = "xsd2java"
    public static final String CLEAN = "cleanGeneratedSources"

    void apply(Project project) {
        // make sure the project has the java plugin
        project.apply(plugin: 'java')

        Configuration wsdl2javaConfiguration = project.configurations.maybeCreate(WSDL2JAVA)
        Configuration xsd2javaConfiguration = project.configurations.maybeCreate(XSD2JAVA)

        // add xsd2java task with group and a description
        project.task(XSD2JAVA,
                type: Xsd2JavaTask,
                group: 'Wsdl2Java',
                description: 'Generate java source code from XSD files.') {
            classpath = xsd2javaConfiguration
        }

        // add wsdl2java task with group and a description
        project.task(WSDL2JAVA,
                type: Wsdl2JavaTask,
                group: 'Wsdl2Java',
                description: 'Generate java source code from WSDL files.') {
            classpath = wsdl2javaConfiguration
        }

        // add cleanXsd task with group and a description
        project.task(CLEAN,
                type: CleanTask,
                group: 'Wsdl2Java',
                description: 'Delete java source code generated from WSDL and XSD files.')

        project.afterEvaluate {
            def cxfVersion = project.wsdl2java.cxfVersion

            // add cxf as dependency
            project.dependencies {
                wsdl2java "org.apache.cxf:cxf-tools:$cxfVersion"
                wsdl2java "org.apache.cxf:cxf-tools-wsdlto-databinding-jaxb:$cxfVersion"
                wsdl2java "org.apache.cxf:cxf-tools-wsdlto-frontend-jaxws:$cxfVersion"
            }

            // add jaxb-xjc
            project.dependencies {
                xsd2java 'com.sun.xml.bind:jaxb-xjc:2.2.4-1'
            }

            // add generated sources to java scrdirs
            project.sourceSets.main.java.srcDirs += project.wsdl2java.generatedWsdlDir
            project.sourceSets.main.java.srcDirs += project.xsd2java.generatedXsdDir

            // make compileJava depend on wsdl2java and xsd2java task
            project.compileJava.dependsOn project.wsdl2java
            project.compileJava.dependsOn project.xsd2java
        }
    }
}