package ipp;

import de.gmuth.ipp.client.CupsClient;
import de.gmuth.ipp.client.IppPrinter;

public class ListCupsPrinters {

  public static void main(String[] args) {
    CupsClient cupsClient = new CupsClient("localhost");
    for (IppPrinter ippPrinter : cupsClient.getPrinters()) {
      System.out.println(ippPrinter.toString());
    }
  }

}