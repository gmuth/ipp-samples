package ipp;

import static de.gmuth.ipp.client.IppTemplateAttributes.documentFormat;
import static de.gmuth.ipp.client.IppTemplateAttributes.jobName;

import de.gmuth.ipp.client.IppColorMode;
import de.gmuth.ipp.client.IppConfig;
import de.gmuth.ipp.client.IppJob;
import de.gmuth.ipp.client.IppPrinter;
import de.gmuth.ipp.client.IppSides;
import java.io.File;

public class PdfPrintJob {

  public static void main(String[] args) {

    IppConfig ippConfig = new IppConfig();
    ippConfig.setChunkedStreamingMode(false);
    //HttpURLConnectionClient.Companion.getLog().setLogLevel(LogLevel.DEBUG);

    IppPrinter ippPrinter = new IppPrinter("ipp://localhost:8632/printers/laser", ippConfig);
    ippPrinter.logDetails();

    File file = new File("test-docs/A4-ten-pages.pdf");
    IppJob job = ippPrinter.printJob(
      file,
      documentFormat("application/pdf"),
      jobName(file.getName()),
      IppColorMode.Monochrome,
      IppSides.TwoSidedLongEdge
    );
    job.waitForTermination();
    job.logDetails();
  }

}