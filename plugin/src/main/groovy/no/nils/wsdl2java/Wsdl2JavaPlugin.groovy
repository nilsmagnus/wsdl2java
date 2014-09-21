package no.nils
import org.gradle.api.Plugin
import org.gradle.api.Project

class Wsdl2JavaPlugin implements Plugin<Project> {
    static final String WSDL2JAVA = "wsdl2java"

    void apply(Project project) {
        // make sure the project has the java plugin
        project.apply(plugin: 'java')

        // add task and a description
        project.task(WSDL2JAVA, type: Wsdl2JavaTask) {
            description 'Generate java source code from wsdl or xsd files, using apache-cxf-tools.'
        }

        // add generated sources to java scrdirs
        project.sourceSets.main.java.srcDirs += project.wsdl2java.generatedWsdlDir

        // make compileJava depend on wsdl2java task
        project.compileJava.dependsOn project.wsdl2java


    }
}