package log

import de.gmuth.log.Logging
import de.gmuth.log.Logging.LogLevel.TRACE

class IppClientLoggingDemo {

    companion object {
        val log = Logging.getLogger { }
    }

    fun log() {
        log.trace { "trace message" }
        log.debug { "debug message" }
        log.info { "info  message" }
        log.warn { "warn  message" }
        log.error { "error message" }
        log.error(null) { "null exception" }
        log.error(IllegalStateException("throwable message")) { "exception" }
        log.log(TRACE) { "trace message" }
    }
}

fun main() {
    Logging.defaultLogLevel = TRACE
    IppClientLoggingDemo().log()
}