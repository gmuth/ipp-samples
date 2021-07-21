package log

import de.gmuth.log.Logging

//import de.gmuth.log.Slf4jLogging

fun main() {
    //Slf4jLogging.configure() // 2.1
    Logging.configureSlf4j() // 2.2
    IppClientLoggingDemo().log()
}