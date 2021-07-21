package log

class Slf4kDemo {

    companion object {
        val log = Slf4k.getLogger { }
    }

    fun log() {
        log.trace { "trace message" }
        log.debug { "debug message" }
        log.info { "info  message" }
        log.warn { "warn  message" }
        log.error { "error message" }
        log.error { null }
        log.error(null) { "null exception" }
        log.error(IllegalStateException("throwable message")) { "IllegalStateException" }
    }
}

fun main() {
    Slf4kDemo().log()
}