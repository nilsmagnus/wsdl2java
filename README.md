wsdl2java gradle plugin
=========

[![Known Vulnerabilities](https://snyk.io/test/github/nilsmagnus/wsdl2java/badge.svg?targetFile=build.gradle)](https://snyk.io/test/github/nilsmagnus/wsdl2java?targetFile=build.gradle)
[![Build Status](https://cloud.drone.io/api/badges/nilsmagnus/wsdl2java/status.svg)](https://cloud.drone.io/nilsmagnus/wsdl2java)

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
- Manuel Sanches Ortiz, https://github.com/manuelsanchezortiz

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
            classpath 'no.nils:wsdl2java:0.10'
        }
    }
    apply plugin: 'no.nils.wsdl2java'


### Plugin options

| Option | Default value | Description |
| ------ | ------------- | ----------- |
| cxfVersion | "+" | Controls the CXF version used to generate code.
| deleteGeneratedSourcesOnClean | `false` | If you want to delete the generated sources when `clean` task is invoked, set this option to `true`. |


Example of specifying another CXF version:

    wsdl2javaExt {
        cxfVersion = "2.5.1"
    }


Example of deleting the generated sources on clean:

    wsdl2javaExt {
        deleteGeneratedSourcesOnClean = true
    }



### Options for wsdl2java

| Option | Default value | Description |
| ------ | ------------- | ----------- |
| generatedWsdlDir | generatedsources/src/main/java | This is where you want the generated sources to be placed. |
| wsdlDir | src/main/resources | Define the wsdl files directory to support incremental build. This means that the task will be up-to-date if nothing in this directory has changed. |
| wsdlsToGenerate | empty | This is the main input to the plugin that defines the wsdls to process. It is a list of arguments where each argument is a list of arguments to process a wsdl-file. The Wsdl-file with full path is the last argument. The array can be supplied with the same options as described for the maven-cxf plugin(http://cxf.apache.org/docs/wsdl-to-java.html). |
| locale | Locale.getDefault() | The locale for the generated sources – especially the JavaDoc. This might be necessary to prevent differing sources due to several development environments. |
| stabilizeAndMergeObjectFactory| false | If multiple WSDLs target the same package, merge their ObjectFactory.java classes |

Example setting of options:

    wsdl2java {
        generatedWsdlDir = file("my-generated-sources")  // target directory for generated source coude
        wsdlDir = file("src/main/resources/myWsdlFiles") // define to support incremental build
        wsdlsToGenerate = [   //  2d-array of wsdls and cxf-parameters
                    ['src/main/resources/wsdl/firstwsdl.wsdl'],
                    ['-xjc','-b','bindingfile.xml','src/main/resources/wsdl/secondwsdl.wsdl']
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
            classpath 'no.nils:wsdl2java:0.10'
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
                ['-p','com.acme.mypackage', '-autoNameResolution',"$projectDir/src/main/resources/wsdl/stockqoute.wsdl"]
        ]
        generatedWsdlDir = file("$projectDir/generatedsources")
        wsdlDir = file("$projectDir/src/main/resources/wsdl")
        locale = Locale.FRANCE
    }

    wsdl2javaExt {
        cxfVersion = "2.5.1"
    }

### >= Java 9 support

If you're building the example with JDK 9 make sure to include the following dependencies explicitly:

```gradle
    // the dependency for the task also needs to be declared
    wsdl2java 'com.sun.xml.bind:jaxb-xjc:2.3.0.1'
    wsdl2java 'javax.xml.bind:jaxb-api:2.3.1'
    wsdl2java 'javax.xml.ws:jaxws-api:2.3.1'
    wsdl2java 'org.apache.cxf:cxf-rt-wsdl:3.2.7'
    wsdl2java 'javax.jws:javax.jws-api:1.1'

    implementation 'com.sun.xml.bind:jaxb-xjc:2.3.0.1'
    implementation 'javax.xml.bind:jaxb-api:2.3.1'
    implementation 'javax.xml.ws:jaxws-api:2.3.1'
    implementation 'org.apache.cxf:cxf-rt-wsdl:3.2.7'
    implementation 'javax.jws:javax.jws-api:1.1'
```


## Enable basic extension support for xjc

Apache CXF supports [extension for xjc](http://confluence.highsource.org/display/J2B/JAXB2+Basics+Plugins), e.g. for creating a hashCode, equals and toString method in the classes generated by xjc.  
To use those extensions some more dependencies are necessary.

    dependencies() {
        testCompile 'junit:junit:+'

        compile 'org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.11.0'

        // enable extension support for wsdl2java
        wsdl2java 'org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.11.0'
        wsdl2java 'org.jvnet.jaxb2_commons:jaxb2-basics:0.11.0'
    }

    wsdl2java{
        wsdlsToGenerate = [
            ['-xjc-Xequals', '-xjc-XhashCode', 'src/main/resources/com/example/api/interface.wsdl']
        ]
        generatedWsdlDir = file("$projectDir/generated")
    }

This example creates the hashCode and the equals method.

### A notice on multi-module projects

Instead of referring to absolute paths in your build-file, try using $projectDir as a prefix to your files and directories. As shown in the "Complete example usage".
