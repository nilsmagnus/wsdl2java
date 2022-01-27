package no.nils.wsdl2java

import com.commentremover.app.CommentProcessor
import com.commentremover.app.CommentRemover
import groovy.io.FileType
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.*

import java.nio.charset.Charset
import java.nio.file.Files
import java.security.MessageDigest

@CacheableTask
class Wsdl2JavaTask extends DefaultTask {
    private static final NEWLINE = System.getProperty("line.separator")


    @InputFiles
    @Classpath
    Configuration classpath

    @Internal
    ClassLoader classLoader

    @Nested
    Wsdl2JavaPluginExtension extension

    @TaskAction
    def wsdl2java() {
        deleteOutputFolders()
        MessageDigest md5 = MessageDigest.getInstance("MD5")

        File tmpDir = new File(project.getBuildDir(), "wsdl2java")
        tmpDir.deleteDir()

        if (classpath == null) {
            classpath = project.configurations.getByName(Wsdl2JavaPlugin.WSDL2JAVA)
        }
        setupClassLoader()
        assert classLoader != null
        extension.wsdlsToGenerate.each { args ->
            // Defensively copy the input args, because this might be a immutable implementation.
            def argsCopy = args.collect() as List<Object>

            String wsdlPath = md5.digest(argsCopy[-1].toString().bytes).encodeHex().toString()
            File targetDir = new File(tmpDir, wsdlPath)

            argsCopy.add(argsCopy.size - 1, '-d')
            argsCopy.add(argsCopy.size - 1, targetDir)
            String[] wsdl2JavaArgs = new String[argsCopy.size()]
            for (int i = 0; i < argsCopy.size(); i++)
                wsdl2JavaArgs[i] = argsCopy[i]

            def wsdlToJava = classLoader.loadClass("org.apache.cxf.tools.wsdlto.WSDLToJava").getConstructor().newInstance()
            def toolContext = classLoader.loadClass("org.apache.cxf.tools.common.ToolContext").getConstructor().newInstance()
            wsdlToJava.args = wsdl2JavaArgs

            runWithLocale(extension.locale) { ->
                try {
                    wsdlToJava.run(toolContext)
                } catch (Exception e) {
                    throw new TaskExecutionException(this, e)
                }
            }
            if (extension.removeComments) {
                removeComments(targetDir)
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

    protected void runWithLocale(Locale locale, Closure<Void> closure) {
        // save the current default locale – will be set back at the end
        Locale currentDefaultLocale = Locale.getDefault()
        try {
            // set the wanted locale for the generated java classes
            Locale.setDefault(locale)

            closure()
        }
        finally {
            // set the default locale back to the previous default
            Locale.setDefault(currentDefaultLocale)
        }
    }

    protected void deleteOutputFolders() {
        Set<String> packagePaths = findPackagePaths()
        if (packagePaths.isEmpty()) {
            packagePaths.add("") // add root if no package paths
        }

        Set<File> packageTargetDirs = packagePaths.collect { subPath -> new File(new File(extension.outputDirectory), subPath) }
        getLogger().info("Clear target folders {}", packageTargetDirs)
        getProject().delete(packageTargetDirs)
    }

    private Set<String> findPackagePaths() {
        Set<String> packagePaths = new HashSet<>()
        for (List<String> args : extension.wsdlsToGenerate) {
            int packageArgIdx = args.indexOf("-p")
            int packageIx = packageArgIdx + 1
            if (packageArgIdx != -1 && args.size() >= packageIx) {
                //check if it's wsdl-namespace=package
                String[] maybeWsdlNameSpaceAndPackage = args.get(packageIx).split("=")
                String packageName = maybeWsdlNameSpaceAndPackage.size() == 1 ? maybeWsdlNameSpaceAndPackage[0] : maybeWsdlNameSpaceAndPackage[1]
                String pathPath = packageName.replace(".", "/")
                packagePaths.add(pathPath)
            }
        }
        return packagePaths
    }

    protected void copyToOutputDir(File srcDir) {
        int srcPathLength = srcDir.getAbsolutePath().size() + 1

        srcDir.eachFileRecurse(FileType.FILES) { file ->
            String relPath = file.getAbsolutePath().substring(srcPathLength)
            File target = new File(new File(extension.outputDirectory), relPath)

            switchToEncoding(file)

            if (extension.stabilizeAndMergeObjectFactory) {
                mergeAndStabilizeObjectFactory(file, target)
            } else {
                project.ant.copy(file: file, tofile: target)
            }
        }
    }

    protected void switchToEncoding(File file) {
        List<String> lines = Files.readAllLines(file.toPath(), Charset.defaultCharset())
        file.delete()

        if (extension.stabilize) {
            stripCommentDates(lines)
            stabilizeCommentLinks(file, lines)
            stabilizeXmlElementRef(file, lines)
            stabilizeXmlSeeAlso(file, lines)
            stripFirstLineIfEmpty(lines)
        }
        println("First line is: ${lines.get(0)}")
        String text = lines.join("\n") + "\n"  // want empty line last
        file.withWriter(extension.encoding) { w -> w.write(text) }
    }

    void stripFirstLineIfEmpty(List<String> lines) {
        if (lines.get(0) == null || lines.get(0).length() < 2) {
            println("Removing first line of empty file: ${lines.get(0)}")
            lines.remove(0)
        }else{
            println("Not Removing first line of empty file: ${lines.get(0)}")
        }
    }

    void stripCommentDates(List<String> lines) {
        String prevLine = ""
        for (ListIterator<String> lix = lines.listIterator(); lix.hasNext();) {
            String l = lix.next()
            if (prevLine.contains("This class was generated") && l.startsWith(" * 201")) {
                lix.remove()
            }
            prevLine = l
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

                        List<String> subList = lines.subList(start, end)
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
        String prevLine = ""
        for (ListIterator<String> lix = lines.listIterator(); lix.hasNext();) {
            String l = lix.next()

            if (l.contains("@XmlElementRef") && prevLine.contains("@XmlElementRefs")) {
                int start = lix.previousIndex()

                while (lix.hasNext()) {
                    l = lix.next()
                    if (!l.contains("@XmlElementRef")) {
                        int end = lix.previousIndex()

                        List<String> subList = lines.subList(start, end)
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
            prevLine = l
        }
    }

    protected void mergeAndStabilizeObjectFactory(File src, File target) {
        if (!target.exists()) {
            target.getParentFile().mkdirs()
            project.ant.copy(file: src, tofile: target)
            stabilizeObjFacWithItself(target)
        } else {
            stabilizeObjFacWithTarget(src, target)
        }
    }

    private void stabilizeObjFacWithItself(File target) {
        if (isObjectFactory(target)) {
            getLogger().info(" stabilize ${target}")
            ObjectFactoryMerger.merge(target, target, extension.encoding)
        }
    }

    private stabilizeObjFacWithTarget(File src, File target) {
        if (isObjectFactory(src) && src.getText(extension.encoding) != target.getText(extension.encoding)) {
            getLogger().info(" merge     ${target}")
            ObjectFactoryMerger.merge(src, target, extension.encoding)
        }
    }

    private boolean isObjectFactory(File f) {
        return "ObjectFactory.java".equals(f.getName())
    }

    protected void removeComments(File targetDir) {
        println("Removing comments on ${targetDir.getAbsolutePath()}")
        def commentRemover = new CommentRemover.CommentRemoverBuilder()
                .removeJava(true)
                .removeSingleLines(true)
                .removeMultiLines(true)
                .preserveJavaClassHeaders(false)
                .preserveCopyRightHeaders(false)
                .startExternalPath(targetDir.getAbsolutePath())
                .build()
        new CommentProcessor(commentRemover).start()
    }
}
