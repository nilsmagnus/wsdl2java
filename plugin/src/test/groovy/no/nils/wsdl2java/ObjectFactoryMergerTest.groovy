package no.nils.wsdl2java

import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.junit.Before;

import static org.junit.Assert.*;
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class ObjectFactoryMergerTest{
	File outputDir = new File("build/test")
	
	@Before
	public void cleanOutput() {
		outputDir.deleteDir()
		outputDir.mkdirs()
	} 
	
	@Test
    public void mergeWithEmptyCreatorsWorks() {
		File input = new File("src/test/data/objectfactorymerger/dokumentutil/ObjectFactory.java")
		
		File output = new File(outputDir, "ObjectFactory.java")
		output.withWriter("UTF-8") { w -> w.write(input.getText("UTF-8")) }
		
		ObjectFactoryMerger.merge(input, output, "UTF-8")
		
		File expected = new File("src/test/data/objectfactorymerger/dokumentutil/ObjectFactory_sorted.java")
		assertEquals("Sorting should be stable", expected.getText("UTF-8"), output.getText("UTF-8"))
    }
}