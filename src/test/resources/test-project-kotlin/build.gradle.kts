plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
    id("no.nils.wsdl2java")
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.61")

    testImplementation("junit:junit:4.12")
}

wsdl2java {
    wsdlsToGenerate = listOf(
        listOf(project.file("src/main/resources/custom/stockqoute.wsdl"))
    )
    wsdlDir = project.file("src/main/resources/custom")
    cxfVersion = "+"
}
