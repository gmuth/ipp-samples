package log

object Slf4k {

    enum class LogLevel { TRACE, DEBUG, INFO, WARN, ERROR }

    fun getLogger(noOperation: () -> Unit) = Logger(noOperation.javaClass.enclosingClass.name)

    class Logger(val name: String) {

        private val slf4jLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(name)

        fun trace(messageProducer: () -> Any?) = trace(null, messageProducer)
        fun debug(messageProducer: () -> Any?) = debug(null, messageProducer)
        fun info(messageProducer: () -> Any?) = info(null, messageProducer)
        fun warn(messageProducer: () -> Any?) = warn(null, messageProducer)
        fun error(messageProducer: () -> Any?) = error(null, messageProducer)

        fun trace(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.TRACE, throwable, messageProducer)
        fun debug(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.DEBUG, throwable, messageProducer)
        fun info(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.INFO, throwable, messageProducer)
        fun warn(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.WARN, throwable, messageProducer)
        fun error(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.ERROR, throwable, messageProducer)

        fun log(messageLogLevel: LogLevel, throwable: Throwable?, messageProducer: () -> Any?) {
            with(slf4jLogger) {
                if (when (messageLogLevel) { // is enabled
                            LogLevel.TRACE -> isTraceEnabled
                            LogLevel.DEBUG -> isDebugEnabled
                            LogLevel.INFO -> isInfoEnabled
                            LogLevel.WARN -> isWarnEnabled
                            LogLevel.ERROR -> isErrorEnabled
                        }) {
                    val messageString = messageProducer()?.toString() ?: "null"
                    when (messageLogLevel) { // delegate to slf4j implementation
                        LogLevel.TRACE -> trace(messageString, throwable)
                        LogLevel.DEBUG -> debug(messageString, throwable)
                        LogLevel.INFO -> info(messageString, throwable)
                        LogLevel.WARN -> warn(messageString, throwable)
                        LogLevel.ERROR -> error(messageString, throwable)
                    }
                }
            }
        }
    }
}