package de.gmuth.ipp;

import static de.gmuth.ipp.client.IppTemplateAttributes.documentFormat;
import static de.gmuth.ipp.client.IppTemplateAttributes.jobName;
import static de.gmuth.ipp.client.IppTemplateAttributes.pageRanges;

import de.gmuth.ipp.client.IppColorMode;
import de.gmuth.ipp.client.IppJob;
import de.gmuth.ipp.client.IppPrinter;
import java.io.File;
import kotlin.ranges.IntRange;

public class PrintPdf {

  public static void main(String[] args) {
    IppPrinter ippPrinter = new IppPrinter("ipp://localhost:8632/printers/laser");
    ippPrinter.logDetails();

    File file = new File("test-docs/A4-ten-pages.pdf");
    IppJob job = ippPrinter.printJob(
      file,
      documentFormat("application/pdf"),
      jobName(file.getName()),
      pageRanges(new IntRange(2, 5)),
      IppColorMode.Monochrome
    );
    job.logDetails();

    job.waitForTermination();
    job.logDetails();
  }

}