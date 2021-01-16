package log

import de.gmuth.log.Slf4JLogging

fun main() {
    Slf4JLogging.configure()
    IppClientLoggingDemo().log()
}