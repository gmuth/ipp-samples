package log

import de.gmuth.log.Slf4jAdapter

fun main() {
    //Slf4jLogging.configure() // 2.1
    Slf4jAdapter.configure() // 2.2
    IppClientLoggingDemo().log()
}