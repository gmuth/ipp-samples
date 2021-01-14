import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
}

group = "de.gmuth"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/gmuth/ipp-client-kotlin")
        credentials {
            // set gpr.user and gpr.token in ~/.gradle/gradle.properties
            // gpr.user=myname
            // gpr.token=mytoken
            username = project.findProperty("gpr.user") as String?
            password = project.findProperty("gpr.token") as String?
        }
    }
}

defaultTasks("clean", "compileJava", "compileKotlin")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.6"
    }
}

dependencies {
    implementation("de.gmuth.ipp:ipp-client-kotlin:2.0-SNAPSHOT")
    // the following kotlin dependencies are not required for usage from java
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")

    // mDNS printer discovery - uses slf4j
    implementation("org.jmdns:jmdns:3.5.6")

    //implementation("ch.qos.logback:logback-core:1.2.3")
    // logging implementation
    implementation("ch.qos.logback:logback-classic:1.2.3")

}