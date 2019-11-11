package no.nils.wsdl2java

import org.gradle.internal.impldep.org.apache.commons.io.FileUtils
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

/**
 * Tests the plugin against multiple Gradle versions.
 */
class GradleVersionIT extends Specification {
    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    @Unroll
    def "Should execute wsdl2 and deleteGeneratedSources tasks with Gradle version #gradleVersion"() {
        given:
        FileUtils.copyDirectory(new File("src/test/resources/regular_project"), testProjectDir.root)

        when:
        def wsdl2Result = GradleRunner.create()
                .withGradleVersion(gradleVersion)
                .withProjectDir(testProjectDir.root)
                .withArguments('wsdl2')
                .build()

        then:
        wsdl2Result.task(":wsdl2java").outcome == SUCCESS
        new File(testProjectDir.root, 'generatedsources/wsdl2java/net/webservicex/StockQuote.java').exists()

        when:
        def deleteGeneratedSourcesResult = GradleRunner.create()
                .withGradleVersion(gradleVersion)
                .withProjectDir(testProjectDir.root)
                .withArguments('deleteGeneratedSources')
                .build()

        then:
        deleteGeneratedSourcesResult.task(":deleteGeneratedSources").outcome == SUCCESS
        !new File(testProjectDir.root, 'generatedsources/wsdl2java').exists()

        where:
        gradleVersion << ['4.9']
    }
}
