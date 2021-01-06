package de.gmuth.ipp;

import de.gmuth.ipp.client.IppPrinter;
import de.gmuth.ipp.cups.CupsClient;

public class ListCupsPrinters {

  public static void main(String[] args) {
    CupsClient cupsClient = new CupsClient("localhost");
    for (IppPrinter ippPrinter : cupsClient.getPrinters()) {
      System.out.println(ippPrinter.toString());
    }
  }

}