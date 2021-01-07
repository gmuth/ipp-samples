package mdns

import de.gmuth.ipp.client.IppPrinter
import java.net.URI
import javax.jmdns.JmDNS

fun main() {
    val jmDns = JmDNS.create()
    jmDns.list("_ipp._tcp.local.").forEach {
        val printerName = it.name
        val printerUri = with(it) { URI.create("ipp://$server:$port/${getPropertyString("rp")}") }
        val ippPrinter = IppPrinter(printerUri)
        println("* $printerName")
        println("  $printerUri")
        println("  $ippPrinter")
    }
    jmDns.close()
}