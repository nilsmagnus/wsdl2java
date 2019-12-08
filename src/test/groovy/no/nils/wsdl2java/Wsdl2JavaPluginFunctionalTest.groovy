package no.nils.wsdl2java

import org.gradle.testkit.runner.GradleRunner
import org.junit.Test

import static org.gradle.testkit.runner.TaskOutcome.FROM_CACHE
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.junit.Assert.assertEquals

class Wsdl2JavaPluginFunctionalTest {

    private File projectDir = new File(this.class.classLoader.getResource("test-project").toURI())
    private File kotlinProjectDir = new File(this.class.classLoader.getResource("test-project-kotlin").toURI())

    @Test
    void canRunWsdl2Java() {
        def result = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(projectDir)
                .withArguments("clean", "wsdl2java", "--stacktrace")
                .build()

        assertEquals(SUCCESS, result.task(":wsdl2java").getOutcome())
    }

    @Test
    void canCompileProjectWithGeneratedWsdls() {
        def result = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(projectDir)
                .withArguments("clean", "build", "--stacktrace")
                .build()

        assertEquals(SUCCESS, result.task(":build").getOutcome())
    }

    @Test
    void canCompileKotlinProject() {
        def result = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(kotlinProjectDir)
                .withArguments("clean", "build", "--stacktrace")
                .build()

        assertEquals(SUCCESS, result.task(":build").getOutcome())
    }

    @Test
    void buildCacheWorks() {
        // Delete build cache form previous test runs.
        new File(projectDir, "build-cache").deleteDir()

        def result1 = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(projectDir)
                .withArguments("clean", "wsdl2java", "--build-cache", "--stacktrace")
                .build()

        assertEquals(SUCCESS, result1.task(":wsdl2java").getOutcome())

        def result2 = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(projectDir)
                .withArguments("clean", "wsdl2java", "--build-cache", "--stacktrace")
                .build()

        assertEquals(FROM_CACHE, result2.task(":wsdl2java").getOutcome())
    }
}
