package no.nils.wsdl2java

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CleanTask extends DefaultTask{

      @TaskAction
      public void clean(){
          project.wsdl2java.generatedWsdlDir.deleteDir()
          project.xsd2java.generatedXsdDir.deleteDir()
      }
}