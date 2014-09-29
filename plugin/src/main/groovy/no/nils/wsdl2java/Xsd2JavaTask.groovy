package no.nils.wsdl2java

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.TaskAction

/**
 *
 */
class Xsd2JavaTask extends DefaultTask {

    Configuration classpath


    @TaskAction
    public void xsd2java() {

        ant.taskdef(name: 'xjc', classname: 'com.sun.tools.xjc.XJCTask', classpath: classpath.asPath)
        println "hey ho"

        /*
           xsdsToGenerate.each() { schemaAndPackage ->
         ant.xjc(
         destdir: "$generatedWsdlDir",
         package: schemaAndPackage[1],
         schema: schemaAndPackage[0]
         )
         }
         */
    }
}
