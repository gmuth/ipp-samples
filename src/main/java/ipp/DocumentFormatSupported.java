package ipp;

import de.gmuth.ipp.client.IppPrinter;

public class DocumentFormatSupported {

  public static void main(String[] args) {
    IppPrinter ippPrinter = new IppPrinter("ipp://localhost:8632/printers/laser");
    System.out.println("supported document formats:");
    for (String mimeMediaType : ippPrinter.getDocumentFormatSupported()) {
      System.out.println(mimeMediaType);
    }
  }

//    supported document formats:
//    - application/octet-stream
//    - image/urf
//    - application/pdf
//    - image/jpeg

}