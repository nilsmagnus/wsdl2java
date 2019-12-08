package no.nils.wsdl2java

import org.gradle.api.tasks.*

import java.nio.charset.Charset

class Wsdl2JavaPluginExtension {

    private static final DEFAULT_WSDL_DIR = "src/main/resources/wsdl"

    @Input
    String encoding = Charset.defaultCharset().name()

    @Input
    boolean stabilize = false

    @Input
    boolean stabilizeAndMergeObjectFactory = false

    @Input
    Locale locale = Locale.getDefault()

    @Input
    String cxfVersion = "+"

    @Input
    List<List<Object>> wsdlsToGenerate

    @InputDirectory
    @PathSensitive(PathSensitivity.ABSOLUTE)
    File wsdlDir = new File(DEFAULT_WSDL_DIR)
}
