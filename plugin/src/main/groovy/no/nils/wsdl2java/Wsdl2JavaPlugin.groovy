package no.nils.wsdl2java

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration

class Wsdl2JavaPlugin implements Plugin<Project> {
    public static final String WSDL2JAVA = "wsdl2java"
    public static final String XSD2JAVA = "xsd2java"
    public static final String CLEAN = "deleteGeneratedSources"

    public static final DEFAULT_DESTINATION_DIR = "build/generatedsources/src/main/java"

    void apply(Project project) {
        // make sure the project has the java plugin
        project.apply(plugin: 'java')

        Configuration wsdl2javaConfiguration = project.configurations.maybeCreate(WSDL2JAVA)
        Configuration xsd2javaConfiguration = project.configurations.maybeCreate(XSD2JAVA)

        // add xsd2java task with group and a description
        project.task(XSD2JAVA,
                type: Xsd2JavaTask,
                group: 'Wsdl2Java',
                description: 'Generate java source code from XSD files.') {
            classpath = xsd2javaConfiguration
        }

        // add wsdl2java task with group and a description
        project.task(WSDL2JAVA,
                type: Wsdl2JavaTask,
                group: 'Wsdl2Java',
                description: 'Generate java source code from WSDL files.') {
            classpath = wsdl2javaConfiguration
        }

        // add cleanXsd task with group and a description
        project.task(CLEAN,
                type: CleanTask,
                group: 'Wsdl2Java',
                description: 'Delete java source code generated from WSDL and XSD files.')

        project.afterEvaluate {
            def cxfVersion = project.wsdl2java.cxfVersion

            // add cxf as dependency
            project.dependencies {
                wsdl2java "org.apache.cxf:cxf-tools:$cxfVersion"
                wsdl2java "org.apache.cxf:cxf-tools-wsdlto-databinding-jaxb:$cxfVersion"
                wsdl2java "org.apache.cxf:cxf-tools-wsdlto-frontend-jaxws:$cxfVersion"
                if (project.wsdl2java.wsdlsToGenerate.collect { it.contains('-xjc-Xts') }.contains(true)) {
                    wsdl2java "org.apache.cxf.xjcplugins:cxf-xjc-ts:$cxfVersion"
                }
                if (project.wsdl2java.wsdlsToGenerate.collect { it.contains('-xjc-Xbg') }.contains(true)) {
                    wsdl2java "org.apache.cxf.xjcplugins:cxf-xjc-boolean:$cxfVersion"
                }
            }

            // add jaxb-xjc
            project.dependencies {
                xsd2java 'com.sun.xml.bind:jaxb-xjc:2.2.5'
                xsd2java 'com.sun.xml.bind:jaxb-impl:2.2.5'
            }

            // hook xsd2java into build cycle only if used
            if(project.xsd2java.xsdsToGenerate != null && project.xsd2java.xsdsToGenerate.size() > 0){
                project.sourceSets.main.java.srcDirs += project.xsd2java.generatedXsdDir
                project.compileJava.dependsOn project.xsd2java
            }

            // hook wsdl2java into build cycle only if used
            if(project.wsdl2java.wsdlsToGenerate != null && project.wsdl2java.wsdlsToGenerate.size() >0 ){
                project.sourceSets.main.java.srcDirs += project.wsdl2java.generatedWsdlDir
                project.compileJava.dependsOn project.wsdl2java
            }

            project.clean.dependsOn project.deleteGeneratedSources
        }
    }
}