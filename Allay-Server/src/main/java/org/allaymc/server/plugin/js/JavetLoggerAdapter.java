package org.allaymc.server.plugin.js;

import com.caoccao.javet.interfaces.IJavetLogger;
import org.slf4j.Logger;

public class JavetLoggerAdapter implements IJavetLogger {
    final Logger logger;
    public JavetLoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Throwable cause) {
        logger.error(message, cause);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }
}
