package de.gmuth.ipp

import de.gmuth.ipp.client.IppPrinter

fun main() {
    val ippPrinter = IppPrinter("ipp://localhost:8632/printers/laser")
    val ippVersionsSupported: List<String> = ippPrinter.attributes.getValues("ipp-versions-supported")
    println("ipp versions supported: $ippVersionsSupported")
}