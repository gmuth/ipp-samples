package de.gmuth.ipp;

import de.gmuth.ipp.client.IppPrinter;
import de.gmuth.ipp.cups.CupsMarker;

public class MarkerLevels {

  public static void main(String[] args) {
    IppPrinter ippPrinter = new IppPrinter("ipp://colorjet.local/ipp/printer");
    for (CupsMarker marker : ippPrinter.getMarkers()) {
      System.out.println(marker.toString());
    }
  }

}