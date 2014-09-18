wsdl2java
=========

Gradle plugin for generating java from wsdl/xsd

# About
## Author
Nils Larsgård. 

## Issues
If you have any issues with the plugin, please file an issue at github, https://github.com/nilsmagnus/wsdl2java/issues

## Contribution
Contributions are welcome as long as they are sane. 

## CXF
This library uses the apache-cxf tools to do the actual work. 

# Usage

To use this plugin, you must
- modify your buildscript to have dependencies to the plugin
- apply the plugin
- set the properties of the plugin
- add the generated sources to your sourceset

## Applying the plugin

    buildscript{
        repositories{
            jcenter()
            mavenCentral()
        }
        dependencies {
            classpath 'no.nils:wsdl2Java:+'
        }
    }
    apply plugin: 'wsdl2java'

## Properties
There are two properties that you can set
- generatedWsdlDir : this is where you want the generated sources to be placed. Default value is "generated-sources/src/main/java".
- wsdlsToGenerate : this is the main input to the plugin that defines the wsdls to process. It is a list of arguments where each argument is a list of arguments to process a wsdl-file. The Wsdl-file with full path is the last argument. The array can be supplied with the same options as described for the maven-cxf plugin(http://cxf.apache.org/docs/wsdl-to-java.html). 

    String generatedWsdlDir  // target directory for generated source coude
    def wsdlsToGenerate = [   //  2d-array of wsdls and cxf-parameters
                ['src/main/resources/wsdl/firstwsdl.wsdl'],
                ['-xcj','-b','bingingfile.xml','src/main/resources/wsdl/secodwsdl.wsdl']
            ]

## Add generated sources to sourceset

    sourceSets{
        main.java.srcDirs +=[wsdl2java.generatedWsdlDir]
    }

# Complete example usage
This is a an example of a working build.gradle for a java project. You can also take a look at this projects submodule "consumer" which has a working wsdl compiling.

    buildscript{
        repositories{
            jcenter()
            mavenCentral()
        }
        dependencies {
            classpath 'no.nils:wsdl2Java:+'
        }
    }

    apply plugin :'java'
    apply plugin :'wsdl2java'

    repositories{
        mavenCentral()
    }

    dependencies(){
        testCompile 'junit:junit:+'
    }

    wsdl2java{
        wsdlsToGenerate = [
                ['src/main/resources/wsdl/stockqoute.wsdl']
        ]
        generatedWsdlDir = "generatedsources"
    }

    sourceSets{
        main.java.srcDirs +=[wsdl2java.generatedWsdlDir]
    }

