package no.nils.wsdl2java

import org.gradle.api.tasks.*

import java.nio.charset.Charset

class Wsdl2JavaPluginExtension {

    private static final DEFAULT_WSDL_DIR = "src/main/resources/wsdl"

    @InputDirectory
    @PathSensitive(PathSensitivity.ABSOLUTE)
    File wsdlDir = new File(DEFAULT_WSDL_DIR)

    @Input
    List<List<Object>> wsdlsToGenerate

    @Input
    Locale locale = Locale.getDefault()

    @Input
    String encoding = Charset.defaultCharset().name()

    @Input
    boolean stabilize = false

    @Input
    boolean stabilizeAndMergeObjectFactory = false

    @Input
    String cxfVersion = "+"

    @Input
    String cxfPluginVersion = "+"

}
