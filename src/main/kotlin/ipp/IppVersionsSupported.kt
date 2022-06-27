package ipp

import de.gmuth.ipp.client.IppPrinter
import ipp.Printers.laser

fun main() {
    val ippPrinter = IppPrinter(laser)
    val ippVersionsSupported: List<String> = ippPrinter.attributes.getValues("ipp-versions-supported")
    println("ipp versions supported: $ippVersionsSupported")

    // ipp versions supported: [1.0, 1.1, 2.0]
}