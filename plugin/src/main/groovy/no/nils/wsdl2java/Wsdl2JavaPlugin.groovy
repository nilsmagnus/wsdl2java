package org.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin

class Wsdl2JavaPlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('wsdl2java', type: Wsdl2JavaTask)
        target.compileJava.dependsOn target.wsdl2java
        target.sourceSets.main.java.srcDirs += target.wsdl2java.generatedWsdlDir
        // TODO this does not work. The intention was to let the user decide the version of cxf, but is not currently working.
        //target.afterEvaluate {
        //	target.dependencies.add("compile", "org.apache.cxf:cxf-tools:$target.wsdl2java.cxfVersion")
        //}
    }

   
}