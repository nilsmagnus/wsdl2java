### Note

* Version 3.0.0 contains a breaking change: 'cxfVersion' and 'cxfPluginVersion' properties are now required.
* This plugin is forked from deprecated nilsmagnus/wsdl2java to make the plugin compatible with Gradle 7+. 

wsdl2java gradle plugin
=========
Gradle plugin for generating java from wsdl, using cxf under the hood and the same options as the maven wsdl-2-java plugin from apache-cxf.

### Issues
If you have any issues with the plugin, please file an issue at github, https://github.com/yupzip/wsdl2java/issues

### Contribution
Contributions are welcome as long as they are sane.

#### Contributors
- Peter Vermes , https://github.com/yupzip
- Nicklas Bondesson , https://github.com/nicklasbondesson

### CXF
This plugin uses the apache-cxf tools to do the actual work.
(Version must be defined in gradle task config!)

### Tasks

| Name | Description | Dependecy |
| ---- | ----------- | --------- |
| wsdl2javaTask | Generate java source from wsdl-files | CompileJava/CompileKotlin depends on wsdl2java |

## Usage

To use this plugin, you must
- modify your buildscript to have dependencies to the plugin
- apply the plugin
- set the properties of the plugin

### Applying the plugin

Groovy:

```groovy
plugins {
    id 'java'
    id 'com.yupzip.wsdl2java' version '3.0.0'
}
```

Kotlin:

```kotlin
plugins {
    id("java")
    id("com.yupzip.wsdl2java") version "3.0.0"
}
```

### Plugin options

| Option | Default value | Description |
| ------ | ------------- | ----------- |
| wsdlDir | src/main/resources | Define the wsdl files directory to support incremental build. This means that the task will be up-to-date if nothing in this directory has changed. |
| wsdlsToGenerate | empty | This is the main input to the plugin that defines the wsdls to process. It is a list of arguments where each argument is a list of arguments to process a wsdl-file. The Wsdl-file with full path is the last argument. The array can be supplied with the same options as described for the maven-cxf plugin(http://cxf.apache.org/docs/wsdl-to-java.html). |
| generatedWsdlDir | build/generated/wsdl | Destination directory for generated sources. The task will be up-to-date if nothing in this directory changes between builds. |
| locale | Locale.getDefault() | The locale for the generated sources – especially the JavaDoc. This might be necessary to prevent differing sources due to several development environments. |
| encoding | platform default encoding | Set the encoding name for generated sources, such as EUC-JP or UTF-8. |
| stabilizeAndMergeObjectFactory| false | If multiple WSDLs target the same package, merge their `ObjectFactory` classes. |
| cxfVersion | none, has to be specified | Controls the CXF version used to generate code. |
| cxfPluginVersion | none, has to be specified | Controls the CXF XJC-plugins version used to generate code. |
| includeJava8XmlDependencies | true | If on Java 9 or later this flag includes xml libraries that were previously included with the JRE. Set to false if you use recent versions of Java and the Jakarta xml implementations. |

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
    cxfVersion = "4.0.0"
    cxfPluginVersion = "4.0.0"
}
```
    
Kotlin:

```kotlin
extra["cxfVersion"] = "4.0.0"
extra["cxfPluginVersion"] = "4.0.0"

wsdl2java {
    wsdlDir = file("$projectDir/src/main/wsdl")
    wsdlsToGenerate = listOf(
        listOf("$wsdlDir/firstwsdl.wsdl"),
        listOf("-xjc", "-b", "bindingfile.xml", "$wsdlDir/secondwsdl.wsdl")
    )
}
```

## Example gradle configuration for Spring Boot 3+ with jakarta namespace
```groovy
plugins {
    id "java"
    id "org.springframework.boot" version "3.0.1"
    id "io.spring.dependency-management" version "1.1.0"
    id "com.yupzip.wsdl2java" version "3.0.0"
}

bootJar {
    duplicatesStrategy(DuplicatesStrategy.WARN)
}

compileJava {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    options.compilerArgs << '-parameters'
}

sourceSets.main.java.srcDirs "src/generated-sources/java"

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-web-services'
    implementation 'org.springframework.ws:spring-ws-support:4.0.0'
    // your project dependencies

    implementation 'com.sun.xml.bind:jaxb-impl:4.0.1'
    implementation 'com.sun.xml.messaging.saaj:saaj-impl:3.0.0'
    implementation 'com.sun.xml.ws:jaxws-ri:4.0.0'
    
    implementation 'io.swagger.core.v3:swagger-jaxrs2-jakarta:2.2.7'
    
    implementation 'jakarta.xml.bind:jakarta.xml.bind-api:4.0.0'
    implementation 'jakarta.xml.soap:jakarta.xml.soap-api:3.0.0'
    implementation 'jakarta.xml.ws:jakarta.xml.ws-api:4.0.0'
    
    implementation 'org.jvnet.jaxb2_commons:jaxb2-namespace-prefix:2.0'
    implementation 'codes.rafael.jaxb2_commons:jaxb2-basics:3.0.0'
    implementation 'codes.rafael.jaxb2_commons:jaxb2-basics-runtime:3.0.0'
    
    implementation 'org.apache.cxf.xjc-utils:cxf-xjc-runtime:4.0.0'
    implementation 'org.glassfish.jaxb:jaxb-runtime:4.0.1'
}

configurations {
    wsdl2java
}

dependencies {
        wsdl2java (
        'com.sun.xml.bind:jaxb-impl:4.0.1',
        'org.apache.cxf.xjc-utils:cxf-xjc-runtime:4.0.0',
        'jakarta.xml.ws:jakarta.xml.ws-api:4.0.0',
        'com.sun.xml.ws:rt:4.0.0',
        'org.jvnet.jaxb2_commons:jaxb2-namespace-prefix:2.0',
        'codes.rafael.jaxb2_commons:jaxb2-basics-runtime:3.0.0',
        'codes.rafael.jaxb2_commons:jaxb2-basics:3.0.0'
    )
}

wsdl2java {
    wsdlDir = file("$projectDir/src/main/resources/wsdl/")
    stabilizeAndMergeObjectFactory = true
    includeJava8XmlDependencies = false
    cxfVersion = "4.0.0"
    cxfPluginVersion = "4.0.0"
    wsdlsToGenerate = [
            ['-xjc',
             '-xjc-Xnamespace-prefix',
             '-b',"$projectDir/src/main/resources/wsdl/wsdlBindings.xml",
             '-b',"$projectDir/src/main/resources/wsdl/wsdlTypeDefBindings.xjb",
             '-wsdlLocation', 'classPath:wsdl/myWsdl.wsdl',
             '-p', 'my.package',
             '-autoNameResolution',
             '-verbose',
             "$projectDir/src/main/resources/wsdl/WsgServiceOrderService.wsdl"
            ],
            ['-xjc',
             '-xjc-Xnamespace-prefix',
             '-b',"$projectDir/src/main/resources/wsdl/wsdlBindings2.xml",
             '-b',"$projectDir/src/main/resources/wsdl/wsdlTypeDefBindings2.xjb",
             '-wsdlLocation', 'classPath:wsdl/myWsdl2.wsdl',
             '-p', 'my.package',
             '-autoNameResolution',
             '-verbose',
             "$projectDir/src/main/resources/wsdl/myWsdl2.wsdl"]
    ]
    generatedWsdlDir = file("src/generated-sources/java")
}
```

### Java 9+ support

This plugin automatically adds the necessary dependencies to work on Java 9+ when detected.

As of now, these dependencies are added:

```groovy
implementation "javax.xml.bind:jaxb-api:2.3.1"
implementation "javax.xml.ws:jaxws-api:2.3.1"
implementation "org.glassfish.jaxb:jaxb-runtime:2.3.2"
implementation "org.glassfish.main.javaee-api:javax.jws:3.1.2.2"
implementation "com.sun.xml.messaging.saaj:saaj-impl:1.5.1"
```

## Enable basic extension support for xjc

Apache CXF supports [extension for xjc](http://confluence.highsource.org/display/J2B/JAXB2+Basics+Plugins), e.g. for creating a hashCode, equals and toString method in the classes generated by xjc.  
To use those extensions some more dependencies are necessary.

```groovy
dependencies() {
    implementation 'org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.11.0'

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

This example creates hashCode and equals methods.

### A notice on multi-module projects

Instead of referring to absolute paths in your build-file, try using $projectDir as a prefix to your files and directories. As shown in the "Complete example usage".
