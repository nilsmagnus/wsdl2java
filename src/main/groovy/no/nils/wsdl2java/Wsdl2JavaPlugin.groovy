package org.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin

class Wsdl2JavaPlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('wsdl2java', type: Wsdl2JavaTask)
        target.afterEvaluate {
        	target.configurations.create("wsdl2Java")
        	target.dependencies.add("wsdl2Java", "org.apache.cxf:cxf-tools:$target.wsdl2java.cxfVersion")
        }
    }

   
}