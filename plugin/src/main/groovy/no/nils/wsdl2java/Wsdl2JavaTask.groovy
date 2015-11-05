package no.nils.wsdl2java

import java.io.File
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

import groovy.io.FileType;

class Wsdl2JavaTask extends DefaultTask {
    // user properties
    String cxfVersion = "+"
	String encoding = Charset.defaultCharset().name()
	boolean stabilize = false

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
		MessageDigest md5 = MessageDigest.getInstance("MD5")
		
		File tmpDir = new File(project.getBuildDir(), "wsdl2java")
		tmpDir.deleteDir()
		
        if(classpath == null) {
            classpath = project.configurations.getByName(Wsdl2JavaPlugin.WSDL2JAVA)
        }
        setupClassLoader()
        assert classLoader != null
        wsdlsToGenerate.each { args ->
			String wsdlPath = md5.digest(args[-1].toString().bytes).encodeHex().toString()
			File targetDir = new File(tmpDir, wsdlPath)
			
            args.add(args.size - 1, '-d')
            args.add(args.size - 1, targetDir)
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
			
			copyToOutputDir(targetDir)
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

	protected void copyToOutputDir(File srcDir) {
		int srcPathLength = srcDir.getAbsolutePath().size() + 1
		
		srcDir.eachFileRecurse(FileType.FILES) { file ->
			String relPath = file.getAbsolutePath().substring(srcPathLength)
			File target = new File(generatedWsdlDir, relPath)
			
			switchToEncoding(file)
			project.ant.copy(file: file, tofile: target)
		}
	}

	protected void switchToEncoding(File file) {
		List<String> lines = file.getText().split(System.lineSeparator())
		file.delete()

		if (stabilize) {
			stripCommentDates(lines)
			stabilizeCommentLinks(file, lines)
			stabilizeXmlElementRef(file, lines)
			stabilizeXmlSeeAlso(file, lines)
		}
			
		String text = lines.join(System.lineSeparator()) + System.lineSeparator()  // want empty line last
		file.withWriter(encoding) { w -> w.write(text) }
	}

	void stripCommentDates(List<String> lines) {
		String prevLine = "";
		for (ListIterator<String> lix = lines.listIterator(); lix.hasNext();) {
			String l = lix.next()
			if (prevLine.contains("This class was generated") && l.startsWith(" * 201")) {
				lix.remove();
			}
			prevLine = l;
		}
	}
	
	void stabilizeCommentLinks(File file, List<String> lines) {
		for (ListIterator<String> lix = lines.listIterator(); lix.hasNext();) {
			String l = lix.next()
			
			if (l.contains("* {@link")) {
				int start = lix.previousIndex()
				
				while (lix.hasNext()) {
					l = lix.next()
					if (!l.contains("* {@link")) {
						int end = lix.previousIndex()
						
						List<String> subList = lines.subList(start, end);
						Collections.sort(subList)
						
						break
					}
				}
			}
		}
	}
	
	void stabilizeXmlSeeAlso(File file, List<String> lines) {
		String seeAlsoStart = "@XmlSeeAlso({"
		String seeAlsoEnd = "})"
		for (ListIterator<String> lix = lines.listIterator(); lix.hasNext();) {
			String l = lix.next()
			
			if (l.startsWith(seeAlsoStart) && l.endsWith(seeAlsoEnd)) {
				List<String> classes = l.replace(seeAlsoStart, "").replace(seeAlsoEnd, "").split(",").collect { it.trim() }
				String sortedClasses = seeAlsoStart + classes.sort().join(", ") + seeAlsoEnd
				lix.set(sortedClasses)
			}
		}
	}
	
	void stabilizeXmlElementRef(File file, List<String> lines) {
		String prevLine = "";
		for (ListIterator<String> lix = lines.listIterator(); lix.hasNext();) {
			String l = lix.next()
			
			if (l.contains("@XmlElementRef") && prevLine.contains("@XmlElementRefs")) {
				int start = lix.previousIndex()
	
				while (lix.hasNext()) {
					l = lix.next()
					if (!l.contains("@XmlElementRef")) {
						int end = lix.previousIndex()
						
						List<String> subList = lines.subList(start, end);
						Collections.sort(subList)
						
						// Fix ,-separation of lines
						for (ListIterator<String> subLix = subList.listIterator(); subLix.hasNext();) {
							String line = subLix.next()
							
							line = line.replaceFirst(',$', "")
							if (subLix.hasNext()) {
								line = line + ","
							}
							subLix.set(line)
						}
						break
					}
				}
			}
			prevLine = l;
		}
	}	
}