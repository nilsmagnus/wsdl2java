package org.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class Wsdl2JavaTask extends DefaultTask {
    String greeting = 'hello from wsdl2java'

    @TaskAction
    def wsdl2java() {
        println greeting
    }
}