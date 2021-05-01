package com.yupzip.wsdl2java

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class ObjectFactoryMergerTest {
    File outputDir = new File("build/test")

    @BeforeEach
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
        assertEquals(expected.getText("UTF-8"), output.getText("UTF-8"), "Sorting should be stable")
    }

    @Test
    void mergeWithoutNoCreatorWorks() {
        File input = new File(this.class.classLoader.getResource("objectfactorymerger/autodesktopservice/ObjectFactory.java").toURI())
        File output = new File(outputDir, "ObjectFactory.java")

        output.withWriter("UTF-8") { w -> w.write(input.getText("UTF-8")) }

        ObjectFactoryMerger.merge(input, output, "UTF-8")

        File expected = new File(this.class.classLoader.getResource("objectfactorymerger/autodesktopservice/ObjectFactory_sorted.java").toURI())
        assertEquals(expected.getText("UTF-8"), output.getText("UTF-8"), "Sorting should be stable")
    }
}
