package no.nils.wsdl2java

import org.apache.cxf.tools.common.ToolContext
import org.apache.cxf.tools.wsdlto.WSDLToJava
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

class Wsdl2JavaTask extends DefaultTask {
    String cxfVersion = "+"
    String generatedWsdlDir = "generated-sources/src/main/java"
    def wsdlsToGenerate

    @TaskAction
    def wsdl2java() {
        wsdlsToGenerate.each { args ->
            args.add(args.size - 1, '-d')
            args.add(args.size - 1, generatedWsdlDir)
            String[] wsdl2JavaArgs = new String[args.size()];
            for (int i = 0; i < args.size(); i++)
                wsdl2JavaArgs[i] = args[i]

            WSDLToJava w2j = new WSDLToJava(wsdl2JavaArgs);
            try {
                w2j.run(new ToolContext());
            } catch (Exception e) {
                throw new TaskExecutionException(this, e)
            }
        }
    }
}