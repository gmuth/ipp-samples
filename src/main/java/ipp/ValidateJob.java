package ipp;

import static de.gmuth.ipp.client.IppTemplateAttributes.documentFormat;
import static de.gmuth.ipp.client.IppTemplateAttributes.printerResolution;
import static de.gmuth.ipp.core.IppResolution.Unit.DPI;

import de.gmuth.ipp.client.IppColorMode;
import de.gmuth.ipp.client.IppExchangeException;
import de.gmuth.ipp.client.IppMedia;
import de.gmuth.ipp.client.IppPrinter;
import de.gmuth.ipp.client.IppSides;
import de.gmuth.ipp.core.IppAttribute;
import de.gmuth.ipp.core.IppResponse;

public class ValidateJob {

  public static void main(String[] args) {
    IppPrinter ippPrinter = new IppPrinter("ipp://localhost:8632/printers/laser");

    IppResponse ippValidationResponse;
    try {
      ippValidationResponse = ippPrinter.validateJob(
        documentFormat("application/pdf"),
        printerResolution(600, DPI),
        IppColorMode.Color,
        IppSides.TwoSidedLongEdge,
        new IppMedia.Collection(null, null, "manual", null)
      );

    } catch (IppExchangeException ippExchangeException) {
      ippExchangeException.logDetails();
      System.out.println(ippExchangeException);
      ippValidationResponse = ippExchangeException.getIppResponse();
    }

    System.out.println("status: " + ippValidationResponse.getStatus());
    if (ippValidationResponse.getUnsupportedGroup().size() > 0) {
      System.out.println("unsupported attributes or values:");
      for (IppAttribute ippAttribute : ippValidationResponse.getUnsupportedGroup().values()) {
        System.out.println(ippAttribute);
      }
    }
  }

}