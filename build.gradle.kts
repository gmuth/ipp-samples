import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
}

group = "de.gmuth"
version = "1.0"

repositories {
    // known issue: SNAPSHOTS from github packages are not found by gradle (maven build works!)
    // https://www.flowsquad.io/blog/2020-05-29-devops-mit-github-teil-1-github-packages-mit-gradle/
    // solution: gradle --refresh-dependencies clean build
    maven {
        url = uri("https://maven.pkg.github.com/gmuth/ipp-client-kotlin")
        credentials {
            // set gpr.user and gpr.token in ~/.gradle/gradle.properties
            // gpr.user=myname
            // gpr.token=mytoken
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.token") as String? ?: System.getenv("PACKAGE_READ_TOKEN")
        }
        //mavenContent {snapshotsOnly()}
        //metadataSources {artifact()}
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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    implementation("org.jmdns:jmdns:3.5.6")
    //implementation("de.gmuth.ipp:ipp-client-kotlin:2.1")
    implementation("de.gmuth.ipp:ipp-client-kotlin:2.2-SNAPSHOT") // SNAPSHOTS not found due to github package issue, must install manually
    implementation("ch.qos.logback:logback-classic:1.2.3")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "ipp.InspectPrinterKt"
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().map { zipTree(it) }
    })
}

// gw --refresh-dependencies clean build
// configurations.all {
//    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
//    resolutionStrategy.cacheDynamicVersionsFor(0, "seconds")
// }
