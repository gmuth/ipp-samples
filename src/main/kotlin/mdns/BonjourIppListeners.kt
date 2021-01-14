package mdns

import de.gmuth.ipp.client.IppPrinter
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceInfo
import javax.jmdns.ServiceListener

object BonjourIppListener : ServiceListener {

    private val log: Logger = LoggerFactory.getLogger(BonjourIppListener.javaClass)
    private var lastEvent = System.currentTimeMillis()
    val services = LinkedHashMap<String, ServiceInfo>()

    fun idle() = System.currentTimeMillis() - lastEvent

    fun listen(maxIdle: Long = 1000) {
        val jmDns = JmDNS.create()
        jmDns.addServiceListener("_ipp._tcp.local.", this)
        do runBlocking { delay(100) }
        while (idle() < maxIdle) // nothing happened for the last seconds
        jmDns.close()
    }

    fun listServices() {
        services.values.forEach {
            val printerName = it.name
            val printerUri = with(it) { URI.create("ipp://$server:$port/${getPropertyString("rp")}") }
            val ippPrinter = IppPrinter(printerUri)
            log.info("* $printerName")
            log.info("  $printerUri")
            log.info("  $ippPrinter")
        }
    }

    override fun serviceAdded(event: ServiceEvent) {
        lastEvent = System.currentTimeMillis()
        log.debug("service added: {}", event.info.name)
    }

    override fun serviceResolved(event: ServiceEvent) {
        lastEvent = System.currentTimeMillis()
        log.debug("service resolved: {}", event.info.name)
        services[event.name] = event.info
    }

    override fun serviceRemoved(event: ServiceEvent) {
        lastEvent = System.currentTimeMillis()
        log.debug("service removed: {}", event.info.name)
    }
}

fun main() {
    BonjourIppListener.listen()
    BonjourIppListener.listServices()
}