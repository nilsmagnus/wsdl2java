package no.nils.wsdl2java

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class WsdlCleanTask extends DefaultTask {

    @TaskAction
    void clean() {
        project.wsdl2java.generatedWsdlDir.deleteDir()
    }
}
