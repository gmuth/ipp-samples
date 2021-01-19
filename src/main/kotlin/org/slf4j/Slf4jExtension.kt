package org.slf4j

enum class LogLevel { TRACE, DEBUG, INFO, WARN, ERROR }

fun Logger.trace(messageProducer: () -> Any?) = log(LogLevel.TRACE, null, messageProducer)
fun Logger.debug(messageProducer: () -> Any?) = log(LogLevel.DEBUG, null, messageProducer)
fun Logger.info(messageProducer: () -> Any?) = log(LogLevel.INFO, null, messageProducer)
fun Logger.warn(messageProducer: () -> Any?) = log(LogLevel.WARN, null, messageProducer)
fun Logger.error(messageProducer: () -> Any?) = log(LogLevel.ERROR, null, messageProducer)
fun Logger.trace(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.TRACE, throwable, messageProducer)
fun Logger.debug(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.DEBUG, throwable, messageProducer)
fun Logger.info(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.INFO, throwable, messageProducer)
fun Logger.warn(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.WARN, throwable, messageProducer)
fun Logger.error(throwable: Throwable?, messageProducer: () -> Any?) = log(LogLevel.ERROR, throwable, messageProducer)
fun Logger.log(level: LogLevel, throwable: Throwable?, messageProducer: () -> Any?) {
    if (when (level) { // is enabled
                LogLevel.TRACE -> isTraceEnabled
                LogLevel.DEBUG -> isDebugEnabled
                LogLevel.INFO -> isInfoEnabled
                LogLevel.WARN -> isWarnEnabled
                LogLevel.ERROR -> isErrorEnabled
            }) {
        val messageString = messageProducer()?.toString() ?: "null"
        when (level) { // delegate to slf4j implementation
            LogLevel.TRACE -> trace(messageString, throwable)
            LogLevel.DEBUG -> debug(messageString, throwable)
            LogLevel.INFO -> info(messageString, throwable)
            LogLevel.WARN -> warn(messageString, throwable)
            LogLevel.ERROR -> error(messageString, throwable)
        }
    }
}