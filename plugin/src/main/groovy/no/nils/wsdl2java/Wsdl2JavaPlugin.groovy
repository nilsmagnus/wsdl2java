package no.nils.wsdl2java

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration

class Wsdl2JavaPlugin implements Plugin<Project> {
    public static final String WSDL2JAVA = "wsdl2java"
	public static final String CLEAN = "deleteGeneratedSources"
	public static final String WSDL2JAVA_EXT = "wsdl2javaExt"

    public static final DEFAULT_DESTINATION_DIR = "build/generatedsources/src/main/java"

    void apply(Project project) {
        // make sure the project has the java plugin
        project.apply(plugin: 'java')

		Wsdl2JavaPluginExtension ext = project.extensions.create(WSDL2JAVA_EXT, Wsdl2JavaPluginExtension.class)

        Configuration wsdl2javaConfiguration = project.configurations.maybeCreate(WSDL2JAVA)

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
            def cxfVersion = ext.cxfVersion
            def cxfPluginsVersion = ext.cxfPluginsVersion

            // add cxf as dependency
            project.dependencies {
                wsdl2java "org.apache.cxf:cxf-tools-wsdlto-databinding-jaxb:$cxfVersion"
                wsdl2java "org.apache.cxf:cxf-tools-wsdlto-frontend-jaxws:$cxfVersion"
                if (project.wsdl2java.wsdlsToGenerate.collect { it.contains('-xjc-Xts') }.contains(true)) {
                    wsdl2java "org.apache.cxf.xjcplugins:cxf-xjc-ts:$cxfPluginsVersion"
                }
                if (project.wsdl2java.wsdlsToGenerate.collect { it.contains('-xjc-Xbg') }.contains(true)) {
                    wsdl2java "org.apache.cxf.xjcplugins:cxf-xjc-boolean:$cxfPluginsVersion"
                }
            }

            // hook wsdl2java into build cycle only if used
            if(project.wsdl2java.wsdlsToGenerate != null && project.wsdl2java.wsdlsToGenerate.size() >0 ){
                project.sourceSets.main.java.srcDirs += project.wsdl2java.generatedWsdlDir
                project.compileJava.dependsOn project.wsdl2java
            }

			if (ext.deleteGeneratedSourcesOnClean) {
				project.clean.dependsOn project.deleteGeneratedSources
			}
        }
    }
}
