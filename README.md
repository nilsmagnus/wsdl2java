wsdl2java
=========

Gradle plugin for generating java from wsdl/xsd

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

This is a working build.gradle for a project. You can also take a look at this projects submodule "consumer" which has a working wsdl compiling.

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

