package no.nils.wsdl2java

import org.gradle.api.tasks.*

import java.nio.charset.Charset

class Wsdl2JavaPluginExtension {

    @Input
    String encoding = Charset.defaultCharset().name()

    @Input
    boolean stabilize = false

    @Input
    boolean stabilizeAndMergeObjectFactory = false

    /**
     * The Locale for the generated Java classes.
     */
    @Input
    Locale locale = Locale.getDefault()

    @Input
    String cxfVersion = "+"

    @Internal
    boolean deleteGeneratedSourcesOnClean = false

    @Input
    def wsdlsToGenerate

    @InputDirectory
    @PathSensitive(PathSensitivity.ABSOLUTE)
    File wsdlDir = new File("src/main/resources")

    @OutputDirectory
    File generatedWsdlDir = new File(Wsdl2JavaPlugin.DEFAULT_DESTINATION_DIR)
}
