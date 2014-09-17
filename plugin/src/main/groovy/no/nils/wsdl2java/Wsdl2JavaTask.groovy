package org.gradle

import org.apache.cxf.tools.wsdlto.WSDLToJava
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

import java.io.*

class Wsdl2JavaTask extends DefaultTask {
    String cxfVersion = "+"
    String wsdlDir = "src/resources/wsdl" 
    String generatedWsdlDir = "generated/src/main/java"
	def wsdlsToGenerate
    
    @TaskAction
    def wsdl2java() {
	    if (!new File(wsdlDir).exists()) {
	    	def msg = "wsdlDir '$wsdlDir' does not exist"
	    	throw new TaskExecutionException(this, new IllegalStateException(msg))
	    } else {
	    	wsdlsToGenerate.each{ args ->
                println args
	    		args.add(args.size -1 , '-d')
	    		args.add(args.size -1 , generatedWsdlDir)
                println args.size()
                String[] stockArr = new String[args.size()];
                for(int i=0;i<args.size(); i++)
                    stockArr[i] = args[i]
                WSDLToJava.main(stockArr)
	    	}

	       /* inputs.files wsdlDir.listFiles()
	        outputs.files generatedWsdlDir
	        doLast {
	            wsdlsToGenerate.each { argsin ->
	                argsin.add(argsin.size - 1, '-d')
	                argsin.add(argsin.size - 1, generatedWsdlDir)
	                javaexec {
	                    classpath configurations.ws
	                    main = 'org.apache.cxf.tools.wsdlto.WSDLToJava'
	                    args = argsin
	                    systemProperties = ['exitOnFinish':'TRUE']
	                }
	            }
	        }*/
	    }
	}
}