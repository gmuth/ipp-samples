package log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Slf4jLoggingDemo {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun log() {
        log.trace("trace message")
        log.debug("debug message")
        log.info("info  message")
        log.warn("warn  message")
        log.error("error message")
        log.error("null exception", null)
        log.error("exception", IllegalStateException("throwable message"))
    }
}

fun main() {
    Slf4jLoggingDemo().log()
}