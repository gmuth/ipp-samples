import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/gmuth/ipp-client-kotlin")
        credentials {
            // set gpr.user and gpr.token in ~/.gradle/gradle.properties
            // gpr.username=myname
            // gpr.token=mytoken
            username = project.findProperty("gpr.user") as String?
            password = project.findProperty("gpr.token") as String?
        }
    }
}

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
}