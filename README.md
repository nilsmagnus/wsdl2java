wsdl2java gradle plugin
=========

Gradle plugin for generating java from wsdl/xsd, using cxf under the hood and the same options as the maven wsdl-2-java plugin from apache-cxf.

The plugin binaries are downloadable from bintray: https://bintray.com/nilsmagnus/maven/wsdl2java/


### Issues
If you have any issues with the plugin, please file an issue at github, https://github.com/nilsmagnus/wsdl2java/issues

### Contribution
Contributions are welcome as long as they are sane. 

### CXF
This plugin uses the apache-cxf tools to do the actual work. 

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
            classpath 'no.nils:wsdl2java:+'
        }
    }
    apply plugin: 'no.nils.wsdl2java'
    
    
#### Specific CXF version
If you want to use a specific version of cxf instead of the latest, you could override the plugins dependencies by changing the buildscript block to this, where you replace cxfVersion with your version of cxf:

    buildscript{
        repositories{
            jcenter()
            mavenCentral()
        }
        dependencies {
            classpath 'no.nils:wsdl2java:+'
            classpath ("org.apache.cxf:cxf-tools:$cxfVersion"){ force = true }
            classpath ("org.apache.cxf:cxf-tools-wsdlto-databinding-jaxb:$cxfVersion") { force = true }
            classpath ("org.apache.cxf:cxf-tools-wsdlto-frontend-jaxws:$cxfVersion" ) { force = true }
        }
    }
    apply plugin: 'wsdl2java'
    

### Options
There are two properties that you can set
- generatedWsdlDir : this is where you want the generated sources to be placed. Default value is "generated-sources/src/main/java".
- wsdlsToGenerate : this is the main input to the plugin that defines the wsdls to process. It is a list of arguments where each argument is a list of arguments to process a wsdl-file. The Wsdl-file with full path is the last argument. The array can be supplied with the same options as described for the maven-cxf plugin(http://cxf.apache.org/docs/wsdl-to-java.html). 

Example setting of options:

    wsdl2java{
        String generatedWsdlDir  // target directory for generated source coude
        def wsdlsToGenerate = [   //  2d-array of wsdls and cxf-parameters
                    ['src/main/resources/wsdl/firstwsdl.wsdl'],
                    ['-xcj','-b','bingingfile.xml','src/main/resources/wsdl/secodwsdl.wsdl']
            ]
    }

### Add generated sources to sourceset

    sourceSets{
        main.java.srcDirs +=[wsdl2java.generatedWsdlDir]
    }
    
    

## Complete example usage
This is a an example of a working build.gradle for a java project. You can also take a look at this projects submodule "consumer" which has a working wsdl compiling.

    buildscript{
        repositories{
            jcenter() 
            mavenCentral()
        }
        dependencies {
            classpath 'no.nils:wsdl2java:+'
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
                ['src/main/resources/wsdl/stockqoute.wsdl']
        ]
        generatedWsdlDir = "generatedsources"
    }

    sourceSets{
        main.java.srcDirs +=[wsdl2java.generatedWsdlDir]
    }

