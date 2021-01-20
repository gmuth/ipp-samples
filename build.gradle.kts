import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
}

group = "de.gmuth"
version = "1.0-SNAPSHOT"

println("ACTOR" + System.getenv("GITHUB_ACTOR"))
println("TOKEN" + System.getenv("PACKAGE_READ_TOKEN")?.length)

repositories {
    maven {
        url = uri("https://maven.pkg.github.com/gmuth/ipp-client-kotlin")
        credentials {
            // set gpr.user and gpr.token in ~/.gradle/gradle.properties
            // gpr.user=myname
            // gpr.token=mytoken
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.token") as String? ?: System.getenv("PACKAGE_READ_TOKEN")
        }
    }
    mavenLocal()
    mavenCentral()
}

defaultTasks("clean", "compileJava", "compileKotlin")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.6"
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jmdns:jmdns:3.5.6")
    implementation("de.gmuth.ipp:ipp-client-kotlin:2.0-SNAPSHOT")
}

// gw --refresh-dependencies clean compile
//configurations.all {
//    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
//}
