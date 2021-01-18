package log

import de.gmuth.log.Logging

class IppClientLoggingDemo {

    private val log = Logging.getLogger { }

    fun log() {
        log.trace { "trace message" }
        log.debug { "debug message" }
        log.info { "info  message" }
        log.warn { "warn  message" }
        log.error { "error message" }
        log.error(null) { "null exception" }
        log.error(IllegalStateException("throwable message")) { "exception" }
    }
}

fun main() {
    Logging.defaultLogLevel = Logging.LogLevel.TRACE
    IppClientLoggingDemo().log()
}