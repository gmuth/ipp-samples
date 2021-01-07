package mdns

import java.net.URI
import javax.jmdns.ServiceInfo

fun ServiceInfo.getIppUri(scheme: String = "ipp") = URI.create("$scheme://$server:$port/${getPropertyString("rp")}")