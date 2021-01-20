package ipp

import de.gmuth.ipp.client.IppColorMode
import de.gmuth.ipp.client.IppPrinter
import de.gmuth.ipp.client.IppTemplateAttributes.documentFormat
import de.gmuth.ipp.client.IppTemplateAttributes.jobName
import de.gmuth.ipp.client.IppTemplateAttributes.pageRanges
import de.gmuth.log.Slf4jLogging
import java.io.File

fun main() {
    Slf4jLogging.configure()
    val ippPrinter = IppPrinter("ipp://localhost:8632/printers/laser")
    ippPrinter.logDetails()

    val file = File("test-docs/A4-ten-pages.pdf")
    val job = ippPrinter.printJob(
            file,
            documentFormat("application/pdf"),
            jobName(file.name),
            pageRanges(2..5),
            IppColorMode.Monochrome
    )
    job.logDetails()

    job.waitForTermination()
    job.logDetails()
}