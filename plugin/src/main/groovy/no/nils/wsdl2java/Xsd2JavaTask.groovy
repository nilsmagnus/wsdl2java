package no.nils.wsdl2java

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 *
 */
class Xsd2JavaTask extends DefaultTask {

    @OutputDirectory
    File generatedXsdDir = new File(Wsdl2JavaPlugin.DEFAULT_DESTINATION_DIR)

    Configuration classpath
    def xsdsToGenerate
    def encoding

    @TaskAction
    public void xsd2java() {

        ant.taskdef(name: 'xjc', classname: 'com.sun.tools.xjc.XJCTask', classpath: classpath.asPath)

        if(!generatedXsdDir.exists()){
            generatedXsdDir.mkdirs()
        }

        xsdsToGenerate.each() { schemaAndPackage ->
            def options = [
                destdir: generatedXsdDir,
                package: schemaAndPackage[1],
                schema: schemaAndPackage[0]
            ]
            // adding additional options to xjc task if available
            if(schemaAndPackage.size() > 2) {
                options << schemaAndPackage[2]
            }
            if (encoding != null) {
                options.encoding = encoding
            }
            ant.xjc(
                options  
            )
        }

    }
}
