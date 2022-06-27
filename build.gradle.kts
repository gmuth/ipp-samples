import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.5.32"
}

group = "de.gmuth"
version = "1.0"

repositories {
    //mavenLocal()
    mavenCentral()
    //maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
    // known issue: SNAPSHOTS from github packages are not found by gradle (maven build works!)
    // https://www.flowsquad.io/blog/2020-05-29-devops-mit-github-teil-1-github-packages-mit-gradle/
    // solution: gradle --refresh-dependencies clean build
//    maven {
//        url = uri("https://maven.pkg.github.com/gmuth/ipp-client-kotlin")
//        credentials {
//            // set gpr.user and gpr.token in ~/.gradle/gradle.properties
//            // gpr.user=myname
//            // gpr.token=mytoken
//            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
//            password = project.findProperty("gpr.token") as String? ?: System.getenv("PACKAGE_READ_TOKEN")
//        }
    //mavenContent {snapshotsOnly()}
    //metadataSources {artifact()}
    //   }
}

defaultTasks("clean", "compileJava", "compileKotlin")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    implementation("org.jmdns:jmdns:3.5.6")
    implementation("de.gmuth:ipp-client:2.3") // SNAPSHOTS not found due to github package issue, must install manually
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

// for snapshots: gradlew --refresh-dependencies cK
configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    resolutionStrategy.cacheDynamicVersionsFor(0, "seconds") // this one looks good
}