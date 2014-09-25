package no.nils.wsdl2java
import org.gradle.api.Plugin
import org.gradle.api.Project

class Wsdl2JavaPlugin implements Plugin<Project> {
    public static final String WSDL2JAVA = "wsdl2java"

    void apply(Project project) {
        // make sure the project has the java plugin
        project.apply(plugin: 'java')

        //Configuration config = project.configurations.maybeCreate(WSDL2JAVA)

        // add wsdl2java task with group and a description
        project.task(WSDL2JAVA,
                type: Wsdl2JavaTask,
                group: 'Wsdl2Java',
                description: 'Generate java source code from wsdl files.')

        // add generated sources to java scrdirs
        project.sourceSets.main.java.srcDirs += project.wsdl2java.generatedWsdlDir

        // make compileJava depend on wsdl2java task
        project.compileJava.dependsOn project.wsdl2java

        // TODO this does still not work, ask someone for help/hints on this one
        /*
        project.afterEvaluate {
            def cxfVersion = project.wsdl2java.cxfVersion
            project.dependencies.wsdl2java("org.apache.cxf:cxf-tools:$cxfVersion") { force = true }
            project.dependencies.wsdl2java("org.apache.cxf:cxf-tools-wsdlto-databinding-jaxb:$cxfVersion") {
                force = true
            }
            project.dependencies.wsdl2java("org.apache.cxf:cxf-tools-wsdlto-frontend-jaxws:$cxfVersion") {
                force = true
            }
        }  */
    }
}