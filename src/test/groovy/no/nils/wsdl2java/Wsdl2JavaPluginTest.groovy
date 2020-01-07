package no.nils.wsdl2java

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertTrue

class Wsdl2JavaPluginTest {

    @Test
    void canAddWsdlTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('wsdl2java', type: Wsdl2JavaTask)
        assertTrue(task instanceof Wsdl2JavaTask)
    }

    @Test
    void wsdl2javaPluginAddsWsdl2javaTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.apply(plugin: Wsdl2JavaPlugin)

        assertTrue(project.tasks.wsdl2java instanceof Wsdl2JavaTask)
    }
}
