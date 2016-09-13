package no.nils.wsdl2java

import org.gradle.api.tasks.Input;

class Wsdl2JavaPluginExtension {
	String cxfVersion = "+"
	String cxfPluginsVersion = "+"
	boolean deleteGeneratedSourcesOnClean = false
}
