package org.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin

class Wsdl2JavaPlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('wsdl2java', type: Wsdl2JavaTask)
    }
}