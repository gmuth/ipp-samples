package org.slf4j

import org.slf4j.LogLevel.*

enum class LogLevel { TRACE, DEBUG, INFO, WARN, ERROR }

fun Logger.trace(throwable: Throwable? = null, messageProducer: () -> Any?) = log(TRACE, throwable, messageProducer)
fun Logger.debug(throwable: Throwable? = null, messageProducer: () -> Any?) = log(DEBUG, throwable, messageProducer)
fun Logger.info(throwable: Throwable? = null, messageProducer: () -> Any?) = log(INFO, throwable, messageProducer)
fun Logger.warn(throwable: Throwable? = null, messageProducer: () -> Any?) = log(WARN, throwable, messageProducer)
fun Logger.error(throwable: Throwable? = null, messageProducer: () -> Any?) = log(ERROR, throwable, messageProducer)
fun Logger.log(level: LogLevel, throwable: Throwable?, messageProducer: () -> Any?) {
    if (when (level) { // is enabled
                TRACE -> isTraceEnabled
                DEBUG -> isDebugEnabled
                INFO -> isInfoEnabled
                WARN -> isWarnEnabled
                ERROR -> isErrorEnabled
            }) {
        val message = messageProducer()?.toString()
        when (level) { // delegate to slf4j implementation
            TRACE -> trace(message, throwable)
            DEBUG -> debug(message, throwable)
            INFO -> info(message, throwable)
            WARN -> warn(message, throwable)
            ERROR -> error(message, throwable)
        }
    }
}