package mdns

import de.gmuth.ipp.client.IppPrinter
import javax.jmdns.JmDNS

fun main() {
    val jmDns = JmDNS.create()
    jmDns.list("_ipp._tcp.local.").forEach {
        val printerName = it.name
        val printerUri = it.getIppUri()
        val ippPrinter = IppPrinter(printerUri)
        println("* $printerName")
        println("  $printerUri")
        println("  $ippPrinter")
    }
    jmDns.close()
}