package log

import log.Slf4k.Logger.LogLevel.*

object Slf4k {

    fun getLogger(noOperation: () -> Unit) = Logger(noOperation.javaClass.enclosingClass.name)

    class Logger(val name: String) {

        protected val slf4jLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(name)

        enum class LogLevel { TRACE, DEBUG, INFO, WARN, ERROR }

        fun trace(throwable: Throwable? = null, messageProducer: () -> Any?) = log(TRACE, throwable, messageProducer)
        fun debug(throwable: Throwable? = null, messageProducer: () -> Any?) = log(DEBUG, throwable, messageProducer)
        fun info(throwable: Throwable? = null, messageProducer: () -> Any?) = log(INFO, throwable, messageProducer)
        fun warn(throwable: Throwable? = null, messageProducer: () -> Any?) = log(WARN, throwable, messageProducer)
        fun error(throwable: Throwable? = null, messageProducer: () -> Any?) = log(ERROR, throwable, messageProducer)

        fun log(messageLogLevel: LogLevel, throwable: Throwable? = null, messageProducer: () -> Any?) {
            with(slf4jLogger) {
                if (when (messageLogLevel) { // is enabled
                            TRACE -> isTraceEnabled
                            DEBUG -> isDebugEnabled
                            INFO -> isInfoEnabled
                            WARN -> isWarnEnabled
                            ERROR -> isErrorEnabled
                        }) {
                    val messageString = messageProducer()?.toString()
                    when (messageLogLevel) { // delegate to slf4j implementation
                        TRACE -> trace(messageString, throwable)
                        DEBUG -> debug(messageString, throwable)
                        INFO -> info(messageString, throwable)
                        WARN -> warn(messageString, throwable)
                        ERROR -> error(messageString, throwable)
                    }
                }
            }
        }
    }
}