package ipp

import de.gmuth.ipp.client.IppPrinter
import ipp.Printers.laser

fun main() {
    val ippPrinter = IppPrinter(laser)
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