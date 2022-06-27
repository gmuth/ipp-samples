package ipp

import java.net.URI

object Printers {
    val colorLaser = printerSimulator("colorlaser")
    val laser = printerSimulator("laser")
    val label = printerSimulator("label")
    val roll = printerSimulator("roll")

    fun printerSimulator(printer: String) = URI.create("ipp://localhost:8632/ipp/print/$printer")
}