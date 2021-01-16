package log

import org.slf4j.LoggerFactory

class Slf4jLoggingDemo {

    val log = LoggerFactory.getLogger(this.javaClass)

    fun log() {
        log.trace("trace message")
        log.debug("debug message")
        log.info("info  message")
        log.warn("warn  message")
        log.error("error message")
    }
}

fun main() {
    Slf4jLoggingDemo().log()
}