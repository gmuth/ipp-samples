package log

import de.gmuth.log.Logging

class IppClientLoggingDemo {

    val log = Logging.getLogger { }

    fun log() {
        log.trace { "trace message" }
        log.debug { "debug message" }
        log.info { "info  message" }
        log.warn { "warn  message" }
        log.error { "error message" }
    }
}

fun main() {
    Logging.defaultLogLevel = Logging.LogLevel.TRACE
    IppClientLoggingDemo().log()
}