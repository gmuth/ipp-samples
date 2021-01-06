package ipp

import de.gmuth.ipp.client.IppPrinter

fun main() {
    val ippPrinter = IppPrinter("ipp://localhost:8632/printers/laser")
    ippPrinter.logDetails()

    println("printer attributes:")
    for (ippAttribute in ippPrinter.attributes.values) {
        println("* ${ippAttribute.name} = ${ippAttribute.values.joinToString(",")}")
    }
}