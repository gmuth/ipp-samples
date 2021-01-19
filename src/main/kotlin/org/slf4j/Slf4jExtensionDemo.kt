package org.slf4j

class Slf4jExtensionDemo {

    companion object {
        val log = LoggerFactory.getLogger(this.javaClass)
    }

    fun log() {
        log.trace { "trace message" }
        log.debug { "debug message" }
        log.info { "info  message" }
        log.warn { "warn  message" }
        log.error { "error message" }
        log.error(IllegalStateException("throwable message")) { "exception" }
    }
}

fun main() {
    Slf4jExtensionDemo().log()
}