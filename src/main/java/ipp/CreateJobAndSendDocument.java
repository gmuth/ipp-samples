package ipp;

import static de.gmuth.ipp.client.IppFinishing.StapleTopLeft;
import static de.gmuth.ipp.client.IppTemplateAttributes.documentFormat;
import static de.gmuth.ipp.client.IppTemplateAttributes.finishings;
import static de.gmuth.ipp.client.IppTemplateAttributes.jobName;

import de.gmuth.ipp.client.CupsClient;
import de.gmuth.ipp.client.IppColorMode;
import de.gmuth.ipp.client.IppJob;
import de.gmuth.ipp.client.IppPrinter;
import de.gmuth.log.Logging;
import de.gmuth.log.Logging.Logger;
import java.io.File;
import java.io.FileInputStream;

public class CreateJobAndSendDocument {

  public static void main(String[] args) {

    Logger log = Logging.INSTANCE.getLogger("main");
    try {
      File file = new File("test-docs/A4-ten-pages.pdf");
      CupsClient cupsClient = new CupsClient();
      IppPrinter ippPrinter = cupsClient.getPrinter("Simulated_Color_Laser___SpaceBook");
      IppJob job = ippPrinter.createJob(
        documentFormat("application/pdf"),
        jobName(file.getName()),
        finishings(StapleTopLeft),
        IppColorMode.Color
      );
      job.sendDocument(new FileInputStream(file));
      job.waitForTermination();
      job.logDetails();
    } catch (Exception exception) {
      log.error(exception);
    }
  }

}