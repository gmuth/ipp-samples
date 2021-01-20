package log

import de.gmuth.log.Slf4jLogging

fun main() {
    Slf4jLogging.configure()
    IppClientLoggingDemo().log()
}