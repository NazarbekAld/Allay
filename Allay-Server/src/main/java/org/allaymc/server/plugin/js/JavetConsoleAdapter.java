package org.allaymc.server.plugin.js;

import com.caoccao.javet.interception.logging.BaseJavetConsoleInterceptor;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.values.V8Value;
import lombok.SneakyThrows;
import org.slf4j.Logger;

public class JavetConsoleAdapter extends BaseJavetConsoleInterceptor {
    private final Logger logger;
    /**
     * Instantiates a new Base javet console interceptor.
     *
     * @param v8Runtime the V8 runtime
     *
     * @since 0.7.0
     */
    public JavetConsoleAdapter(V8Runtime v8Runtime, Logger logger) {
        super(v8Runtime);
        this.logger = logger;
    }

    @SneakyThrows
    @Override
    public void consoleDebug(V8Value... v8Values) {
        logger.debug(concat(v8Values));
    }

    @Override
    public void consoleError(V8Value... v8Values) {
        logger.error(concat(v8Values));
    }

    @Override
    public void consoleInfo(V8Value... v8Values) {
        logger.info(concat(v8Values));
    }

    @Override
    public void consoleLog(V8Value... v8Values) {
        logger.info(concat(v8Values));
    }

    @Override
    public void consoleTrace(V8Value... v8Values) {
        logger.trace(concat(v8Values));
    }

    @Override
    public void consoleWarn(V8Value... v8Values) {
        logger.warn(concat(v8Values));
    }
}
