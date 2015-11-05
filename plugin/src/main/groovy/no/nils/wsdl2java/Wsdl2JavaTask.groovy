package no.nils.wsdl2java

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

class Wsdl2JavaTask extends DefaultTask {
    // user properties
    String cxfVersion = "+"

    @InputDirectory
    File wsdlDir = new File("src/main/resources")

    @OutputDirectory
    File generatedWsdlDir = new File(Wsdl2JavaPlugin.DEFAULT_DESTINATION_DIR)

    def wsdlsToGenerate

    // build internal properties
    Configuration classpath
    ClassLoader classLoader

    @TaskAction
    def wsdl2java() {
		deleteOutputFolders()
		
        if(classpath == null) {
            classpath = project.configurations.getByName(Wsdl2JavaPlugin.WSDL2JAVA)
        }
        setupClassLoader()
        assert classLoader != null
        wsdlsToGenerate.each { args ->
            args.add(args.size - 1, '-d')
            args.add(args.size - 1, generatedWsdlDir)
            String[] wsdl2JavaArgs = new String[args.size()];
            for (int i = 0; i < args.size(); i++)
                wsdl2JavaArgs[i] = args[i]

            def wsdlToJava = classLoader.loadClass("org.apache.cxf.tools.wsdlto.WSDLToJava").newInstance()
            def toolContext = classLoader.loadClass("org.apache.cxf.tools.common.ToolContext").newInstance()
            wsdlToJava.args = wsdl2JavaArgs
           // WSDLToJava w2j = new WSDLToJava(wsdl2JavaArgs);
            try {
                wsdlToJava.run(toolContext);
            } catch (Exception e) {
                throw new TaskExecutionException(this, e)
            }
        }
    }

    private void setupClassLoader() {
        if (classpath?.files) {
            def urls = classpath.files.collect { it.toURI().toURL() }

            classLoader = new URLClassLoader(urls as URL[], Thread.currentThread().contextClassLoader)
            Thread.currentThread().contextClassLoader = classLoader
        } else {
            classLoader = Thread.currentThread().contextClassLoader
        }
    }
	
	protected void deleteOutputFolders() {
		Set<String> packagePaths = findPackagePaths();
		if (packagePaths.isEmpty()) {
			packagePaths.add(""); // add root if no package paths
		}

		Set<File> packageTargetDirs = packagePaths.collect { subPath -> new File(generatedWsdlDir, subPath) }
		getLogger().info("Clear target folders {}", packageTargetDirs);
		getProject().delete(packageTargetDirs);
	}

	private Set<String> findPackagePaths() {
		Set<String> packagePaths = new HashSet<>();
		for (List<String> args : wsdlsToGenerate) {
			int packageArgIdx = args.indexOf("-p");
			int packageIx = packageArgIdx+1;
			if (packageArgIdx != -1 && args.size() >= packageIx) {
				String pathPath = args.get(packageIx).replace(".", "/");
				packagePaths.add(pathPath);
			}
		}
		return packagePaths;
	}

}