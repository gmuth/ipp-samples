package ipp;

import de.gmuth.ipp.client.IppPrinter;

public class MarkerLevels {

  public static void main(String[] args) {
    IppPrinter ippPrinter = new IppPrinter("ipp://colorjet.local/ipp/printer");
    ippPrinter.getMarkers().forEach(marker -> System.out.println(marker.toString()));
  }

//  CYAN        48 %       toner  #00FFFF Cyan Cartridge HP CE311A
//  MAGENTA     37 %       toner  #FF00FF Magenta Cartridge HP CE313A
//  YELLOW      43 %       toner  #FFFF00 Yellow Cartridge HP CE312A
//  BLACK        7 % (low) toner  #000000 Black Cartridge HP CE310A
//  NONE        86 %       opc    none    Imaging Drum HP CE314A

}