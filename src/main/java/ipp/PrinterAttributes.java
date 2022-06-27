package ipp;

import de.gmuth.ipp.client.IppPrinter;
import de.gmuth.ipp.core.IppAttribute;

public class PrinterAttributes {

  public static void main(String[] args) {
    IppPrinter ippPrinter = new IppPrinter("ipp://localhost:8632/ipp/print/laser");
    ippPrinter.logDetails();

    System.out.println("printer attributes:");
    for (IppAttribute<?> ippAttribute : ippPrinter.getAttributes().values()) {
      System.out.println(String.format("* %s = %s", ippAttribute.getName(), ippAttribute.getValues()));
    }
  }

}