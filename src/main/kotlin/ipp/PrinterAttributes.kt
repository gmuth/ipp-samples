package ipp

import de.gmuth.ipp.client.IppPrinter
import ipp.Printers.laser

fun main() {
    val ippPrinter = IppPrinter(laser)
    ippPrinter.logDetails()

    println("printer attributes:")
    for (ippAttribute in ippPrinter.attributes.values) {
        println("* ${ippAttribute.name} = ${ippAttribute.values.joinToString(",")}")
    }
}