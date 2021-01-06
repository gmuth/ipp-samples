package ipp

import de.gmuth.ipp.client.IppPrinter

fun main() {
    val ippPrinter = IppPrinter("ipp://localhost:8632/printers/laser")
    println("supported document formats:")
    for (mimeMediaType in ippPrinter.documentFormatSupported) {
        println("- $mimeMediaType")
    }

//    supported document formats:
//    - application/octet-stream
//    - image/urf
//    - application/pdf
//    - image/jpeg
}