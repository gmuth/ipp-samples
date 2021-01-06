package ipp;

import de.gmuth.ipp.client.IppPrinter;
import java.util.List;

public class IppVersionsSupported {

  public static void main(String[] args) {
    IppPrinter ippPrinter = new IppPrinter("ipp://localhost:8632/printers/laser");
    List<String> ippVersionsSupported = ippPrinter.getAttributes().getValues("ipp-versions-supported");
    System.out.println("ipp versions supported: " + ippVersionsSupported);
  }

  // ipp versions supported: [1.0, 1.1, 2.0]

}