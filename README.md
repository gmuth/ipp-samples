# ipp-samples

Sample code written in [java](https://github.com/gmuth/ipp-samples/tree/main/src/main/java/ipp)
and [kotlin](https://github.com/gmuth/ipp-samples/tree/main/src/main/kotlin/ipp)
using my [ipp-client library](https://github.com/gmuth/ipp-client-kotlin).
If the ipp-uri belongs to a real printer the program waits for physical output.

## Java

Print a pdf file and wait for the printer to finish.

```
// initialize printer connection and show printer attributes
IppPrinter ippPrinter = new IppPrinter("ipp://colorjet.local/ipp/printer");
ippPrinter.logDetails();

// print file
File file = new File("my-document.pdf");
IppJob job = ippPrinter.printJob(
  file,
  documentFormat("application/pdf"),
  jobName(file.getName()),
  IppColorMode.Monochrome
);
job.logDetails();

// wait until printer has completed the job
job.waitForTermination();
job.logDetails();

```


## Dependency

[Configure Gradle for use with Github Packages](https://docs.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-gradle-for-use-with-github-packages).
Use this [github package](https://github.com/gmuth/ipp-client-kotlin/packages/214725/versions) of the project as maven repo.
You can access the repo with any github account.
```
repositories {
    jcenter()
    maven {
      url = uri("https://maven.pkg.github.com/gmuth/ipp-client-kotlin")
      credentials {
          // configure gpr.user and gpr.token in ~/.gradle/gradle.properties
          // gpr.user=yourname
          // gpr.token=yourtoken
          username = project.findProperty("gpr.user") as String?
          password = project.findProperty("gpr.token") as String?
      }
    }
}
```

Add dependency:

```
    implementation("de.gmuth.ipp:ipp-client-kotlin:1.9")
or  implementation("de.gmuth.ipp:ipp-client-kotlin:2.0-SNAPSHOT")
```
