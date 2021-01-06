package de.gmuth.ipp

import de.gmuth.ipp.client.IppPrinter

fun main() {
    val ippPrinter = IppPrinter("ipp://localhost:8632/printers/laser")
    println("supported document formats:")
    for (mimeMediaType in ippPrinter.documentFormatSupported) {
        println("- $mimeMediaType")
    }
}