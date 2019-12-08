package no.nils.wsdl2java

import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals

class ObjectFactoryMergerTest {
    File outputDir = new File("build/test")

    @Before
    void cleanOutput() {
        outputDir.deleteDir()
        outputDir.mkdirs()
    }

    @Test
    void mergeWithEmptyCreatorsWorks() {
        File input = new File(this.class.classLoader.getResource("objectfactorymerger/dokumentutil/ObjectFactory.java").toURI())
        File output = new File(outputDir, "ObjectFactory.java")

        output.withWriter("UTF-8") { w -> w.write(input.getText("UTF-8")) }

        ObjectFactoryMerger.merge(input, output, "UTF-8")

        File expected = new File(this.class.classLoader.getResource("objectfactorymerger/dokumentutil/ObjectFactory_sorted.java").toURI())
        assertEquals("Sorting should be stable", expected.getText("UTF-8"), output.getText("UTF-8"))
    }

    @Test
    void mergeWithoutNoCreatorWorks() {
        File input = new File(this.class.classLoader.getResource("objectfactorymerger/autodesktopservice/ObjectFactory.java").toURI())
        File output = new File(outputDir, "ObjectFactory.java")

        output.withWriter("UTF-8") { w -> w.write(input.getText("UTF-8")) }

        ObjectFactoryMerger.merge(input, output, "UTF-8")

        File expected = new File(this.class.classLoader.getResource("objectfactorymerger/autodesktopservice/ObjectFactory_sorted.java").toURI())
        assertEquals("Sorting should be stable", expected.getText("UTF-8"), output.getText("UTF-8"))
    }
}
