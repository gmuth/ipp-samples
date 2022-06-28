[![gradle build](https://github.com/gmuth/ipp-samples/workflows/gradle%20build/badge.svg)](https://github.com/gmuth/ipp-samples/actions?query=workflow%3A%22gradle+build%22)
[![maven build](https://github.com/gmuth/ipp-samples/workflows/maven%20build/badge.svg)](https://github.com/gmuth/ipp-samples/actions?query=workflow%3A%22maven+build%22)

# ipp-samples

Sample code written in [java](https://github.com/gmuth/ipp-samples/tree/main/src/main/java/ipp)
and [kotlin](https://github.com/gmuth/ipp-samples/tree/main/src/main/kotlin/ipp)
using my [ipp-client library](https://github.com/gmuth/ipp-client-kotlin).

## Bonjour Discovery

Use [jmDNS](https://github.com/jmdns/jmdns) to
[discover printers](https://github.com/gmuth/ipp-samples/blob/main/src/main/kotlin/mdns/DiscoverIppPrinters.kt)
via Bonjour including AirPrint compatible printers.

```
val jmDns = JmDNS.create()
jmDns.list("_ipp._tcp.local.").forEach {
    val printerName = it.name
    val printerUri = with(it) { URI.create("ipp://$server:$port/${getPropertyString("rp")}") }
    val ippPrinter = IppPrinter(printerUri)
    println("* $printerName")
    println("  $printerUri")
    println("  $ippPrinter")
}
jmDns.close()
```
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

```
    implementation("de.gmuth:ipp-client:2.3")
```
