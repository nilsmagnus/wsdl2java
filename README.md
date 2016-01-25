wsdl2java gradle plugin
=========

[![Join the chat at https://gitter.im/nilsmagnus/wsdl2java](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/nilsmagnus/wsdl2java?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://drone.io/github.com/nilsmagnus/wsdl2java/status.png)](https://drone.io/github.com/nilsmagnus/wsdl2java/latest)

Gradle plugin for generating java from wsdl, using cxf under the hood and the same options as the maven wsdl-2-java plugin from apache-cxf.

The plugin binaries are downloadable from bintray: https://bintray.com/nilsmagnus/maven/wsdl2java/


### Issues
If you have any issues with the plugin, please file an issue at github, https://github.com/nilsmagnus/wsdl2java/issues

### Contribution
Contributions are welcome as long as they are sane.

#### Contributors
- Nils Larsgård , https://github.com/nilsmagnus
- Mats Faugli, https://github.com/fowlie
- Thorben Schiller, https://github.com/thorbolo
- Stefan Kloe, https://github.com/Pentadrago
- Mattias Rundgren, https://github.com/matrun
- Steffen Döring, https://github.com/s-doering
- Jesper Skov, https://github.com/jskovjyskebankdk

### CXF
This plugin uses the apache-cxf tools to do the actual work.

### Tasks

| Name | Description | Dependecy |
| ---- | ----------- | --------- |
| wsdl2java | Generate java source from wsdl-files | CompileJava depends on wsdl2java |
| ~~xsd2java~~ | ~~Generate java source from xsd-files~~ Removed in version 0.8 | ~~CompileJava depends on xsd2java~~ |
| deleteGeneratedSources | Delete all generated sources | Clean depends on deleteGeneratedSources |

## Usage

To use this plugin, you must
- modify your buildscript to have dependencies to the plugin
- apply the plugin
- set the properties of the plugin
- add the generated sources to your sourceset

### Applying the plugin

    buildscript{
        repositories{
            jcenter()
            mavenCentral()
        }
        dependencies {
            classpath 'no.nils:wsdl2java:0.9'
        }
    }
    apply plugin: 'no.nils.wsdl2java'


### Plugin options

| Option | Default value | Description |
| ------ | ------------- | ----------- |
| cxfVersion | "+" | Controls the CXF version used to generate code.
| deleteGeneratedSourcesOnClean | true | If you want to keep the generated sources under version control, set this option to false. |


Example of specifying another CXF version:

    wsdl2javaExt {
        cxfVersion = "2.5.1"
    }


Example of retaining the generated sources on clean:
    wsdl2javaExt {
        deleteGeneratedSourcesOnClean = false
    }



### Options for wsdl2java

| Option | Default value | Description |
| ------ | ------------- | ----------- |
| generatedWsdlDir | generatedsources/src/main/java | This is where you want the generated sources to be placed. |
| wsdlDir | src/main/resources | Define the wsdl files directory to support incremental build. This means that the task will be up-to-date if nothing in this directory has changed. |
| wsdlsToGenerate | empty | This is the main input to the plugin that defines the wsdls to process. It is a list of arguments where each argument is a list of arguments to process a wsdl-file. The Wsdl-file with full path is the last argument. The array can be supplied with the same options as described for the maven-cxf plugin(http://cxf.apache.org/docs/wsdl-to-java.html). |
| locale | Locale.getDefault() | The locale for the generated sources – especially the JavaDoc. This might be necessary to prevent differing sources due to several development environments. |
Example setting of options:

    wsdl2java {
        generatedWsdlDir = file("my-generated-sources")  // target directory for generated source coude
        wsdlDir = file("src/main/resources/myWsdlFiles") // define to support incremental build
        wsdlsToGenerate = [   //  2d-array of wsdls and cxf-parameters
                    ['src/main/resources/wsdl/firstwsdl.wsdl'],
                    ['-xjc','-b','bingingfile.xml','src/main/resources/wsdl/secodwsdl.wsdl']
            ]
        locale = Locale.GERMANY
    }

### Options for xsd2java (deprecated, separate plugin coming soon)
This will not work for version 0.8+!
| Option | Default value | Description |
| ------ | ------------- | ----------- |
| generatedXsdDir | "generatedsources/src/main/java" | Destination directory for generated sources |
| xsdsToGenerate | null | 2-d array consisting of 2 or 3 values in each array: 1. xsd-file(input), 2. package for the generated sources, 3. (optional) a map containing additional options for the xjc task |
| encoding | platform default encoding | Set the encoding name for generated sources, such as EUC-JP or UTF-8. |

Example setting of options:

    xsd2java{
        encoding = 'utf-8'
        xsdsToGenerate = [
            ["src/main/resources/xsd/CustomersAndOrders.xsd", 'no.nils.xsd2java.sample', [header: false] /* optional map */]
        ]
        generatedXsdDir = file("generatedsources/xsd2java")
    }


## Complete example usage
This is a an example of a working build.gradle for a java project. You can also take a look at this projects submodule "consumer" which has a working wsdl compiling.

    buildscript{
        repositories{
            jcenter()
            mavenCentral()
        }
        dependencies {
            classpath 'no.nils:wsdl2java:0.9'
        }
    }

    apply plugin :'java'
    apply plugin :'no.nils.wsdl2java'

    repositories{
        mavenCentral()
    }

    dependencies(){
        testCompile 'junit:junit:+'
    }

    wsdl2java{
        wsdlsToGenerate = [
                ["$projectDir/src/main/resources/wsdl/stockqoute.wsdl"]
        ]
        generatedWsdlDir = file("$projectDir/generatedsources")
        wsdlDir = file("$projectDir/src/main/resources/wsdl")
        locale = Locale.FRANCE
    }
     
### A notice on multi-module projects

Instead of referring to absolute paths in your build-file, try using $projectDir as a prefix to your files and directories. As shown in the "Complete example usage".
