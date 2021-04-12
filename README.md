### Deprecation notice

This plugin is no longer maintained by its creator since I dont have any interest in using this plugin anymore and find no pleasure in maintaining it for free/fun. Please fork it and use it as you like. The plugin is not published to any relevant plugin-portals. 

wsdl2java gradle plugin
=========

[![Known Vulnerabilities](https://snyk.io/test/github/nilsmagnus/wsdl2java/badge.svg?targetFile=build.gradle)](https://snyk.io/test/github/nilsmagnus/wsdl2java?targetFile=build.gradle)
[![Build Status](https://cloud.drone.io/api/badges/nilsmagnus/wsdl2java/status.svg)](https://cloud.drone.io/nilsmagnus/wsdl2java)
[ ![Download](https://api.bintray.com/packages/nilsmagnus/maven/wsdl2java/images/download.svg?version=0.12) ](https://bintray.com/nilsmagnus/maven/wsdl2java/0.12/link)

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
- Ruben Gees, https://github.com/rubengees
- Stefan Krause-Kjær, https://github.com/KrauseStefan

### CXF
This plugin uses the apache-cxf tools to do the actual work.

### Tasks

| Name | Description | Dependecy |
| ---- | ----------- | --------- |
| wsdl2java | Generate java source from wsdl-files | CompileJava/CompileKotlin depends on wsdl2java |
| ~~xsd2java~~ | ~~Generate java source from xsd-files~~ Removed in version 0.8 | ~~CompileJava depends on xsd2java~~ |

## Usage

To use this plugin, you must
- modify your buildscript to have dependencies to the plugin
- apply the plugin
- set the properties of the plugin

### Applying the plugin

Groovy:

```groovy
buildscript{
    repositories{
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath 'no.nils:wsdl2java:0.12'
    }
}

apply plugin: 'no.nils.wsdl2java'
```

Kotlin:

```kotlin
plugins {
    id("java")
    id("no.nils.wsdl2java") version "0.12"
}
```

### Plugin options

| Option | Default value | Description |
| ------ | ------------- | ----------- |
| wsdlDir | src/main/resources | Define the wsdl files directory to support incremental build. This means that the task will be up-to-date if nothing in this directory has changed. |
| wsdlsToGenerate | empty | This is the main input to the plugin that defines the wsdls to process. It is a list of arguments where each argument is a list of arguments to process a wsdl-file. The Wsdl-file with full path is the last argument. The array can be supplied with the same options as described for the maven-cxf plugin(http://cxf.apache.org/docs/wsdl-to-java.html). |
| locale | Locale.getDefault() | The locale for the generated sources – especially the JavaDoc. This might be necessary to prevent differing sources due to several development environments. |
| encoding | platform default encoding | Set the encoding name for generated sources, such as EUC-JP or UTF-8. |
| stabilizeAndMergeObjectFactory| false | If multiple WSDLs target the same package, merge their `ObjectFactory` classes. |
| cxfVersion | "+" | Controls the CXF version used to generate code. |
| cxfPluginVersion | "+" | Controls the CXF XJC-plugins version used to generate code. |

Example setting of options:

Groovy:

```groovy
wsdl2java {
    wsdlDir = file("src/main/resources/myWsdlFiles") // define to support incremental build
    wsdlsToGenerate = [   //  2d-array of wsdls and cxf-parameters
            ['src/main/resources/wsdl/firstwsdl.wsdl'],
            ['-xjc','-b','bindingfile.xml','src/main/resources/wsdl/secondwsdl.wsdl']
    ]
    locale = Locale.GERMANY
    cxfVersion = "2.5.1"
    cxfPluginVersion = "2.4.0"
}
```
    
Kotlin:

```kotlin
extra["cxfVersion"] = "3.3.2"
extra["cxfPluginVersion"] = "3.2.2"

wsdl2java {
    wsdlDir = file("$projectDir/src/main/wsdl")
    wsdlsToGenerate = listOf(
        listOf("$wsdlDir/firstwsdl.wsdl"),
        listOf("-xjc", "-b", "bindingfile.xml", "$wsdlDir/secondwsdl.wsdl")
    )
}
```

### Options for xsd2java (deprecated, separate plugin coming soon)

This will not work for version 0.8+!

| Option | Default value | Description |
| ------ | ------------- | ----------- |
| generatedXsdDir | "generatedsources/src/main/java" | Destination directory for generated sources |
| xsdsToGenerate | null | 2-d array consisting of 2 or 3 values in each array: 1. xsd-file(input), 2. package for the generated sources, 3. (optional) a map containing additional options for the xjc task |
| encoding | platform default encoding | Set the encoding name for generated sources, such as EUC-JP or UTF-8. |

Example setting of options:

```groovy
xsd2java {
    encoding = 'utf-8'
    xsdsToGenerate = [
        ["src/main/resources/xsd/CustomersAndOrders.xsd", 'no.nils.xsd2java.sample', [header: false] /* optional map */]
    ]
    generatedXsdDir = file("generatedsources/xsd2java")
}
```

## Complete example usage
This is a an example of a working build.gradle for a java project. You can also take a look at the test resources, which contain two working projects.

```groovy
buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath 'no.nils:wsdl2java:0.12'
    }
}

apply plugin: 'java'
apply plugin: 'no.nils.wsdl2java'

repositories {
    mavenCentral()
}

dependencies {
    testCompile 'junit:junit:+'
}

wsdl2java {
    wsdlsToGenerate = [
            ['-p', 'com.acme.mypackage', '-autoNameResolution', "$projectDir/src/main/resources/wsdl/stockqoute.wsdl"]
    ]
    wsdlDir = file("$projectDir/src/main/resources/wsdl")
    locale = Locale.FRANCE
    cxfVersion = "2.5.1"
    cxfPluginVersion = "2.4.0"
}
```

### Java 9+ support

This plugin automatically adds the necessary dependencies to work on Java 9+ when detected.

As of now, these dependencies are added:

```groovy
implementation "javax.xml.bind:jaxb-api:2.3.1",
implementation "javax.xml.ws:jaxws-api:2.3.1",
implementation "org.glassfish.jaxb:jaxb-runtime:2.3.2",
implementation "org.glassfish.main.javaee-api:javax.jws:3.1.2.2",
implementation "com.sun.xml.messaging.saaj:saaj-impl:1.5.1"
```

## Enable basic extension support for xjc

Apache CXF supports [extension for xjc](http://confluence.highsource.org/display/J2B/JAXB2+Basics+Plugins), e.g. for creating a hashCode, equals and toString method in the classes generated by xjc.  
To use those extensions some more dependencies are necessary.

```groovy
dependencies() {
    compile 'org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.11.0'

    // enable extension support for wsdl2java
    wsdl2java 'org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.11.0'
    wsdl2java 'org.jvnet.jaxb2_commons:jaxb2-basics:0.11.0'
}

wsdl2java{
    wsdlsToGenerate = [
        ['-xjc-Xequals', '-xjc-XhashCode', 'src/main/resources/com/example/api/interface.wsdl']
    ]
}
```

This example creates the hashCode and the equals method.

### A notice on multi-module projects

Instead of referring to absolute paths in your build-file, try using $projectDir as a prefix to your files and directories. As shown in the "Complete example usage".


# Releasing

* set version to final in build.gradle & commit

* build artifact and upload

    export BINTRAY_USER=<bintrayuser>
    export BINTRAY_API_KEY=<apikey>
    ./gradlew clean bintrayPublish bintrayUpload
	
* increment version and set to SNAPSHOT & commit
* git push
