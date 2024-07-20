package org.allaymc.server.plugin.js;

import com.caoccao.javet.annotations.V8Function;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.values.V8Value;
import com.caoccao.javet.values.reference.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.Plugin;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsPluginPromiseModule {

    private final V8Runtime runtime;
    private final V8ValueObject object;

    public JsPluginPromiseModule(V8Runtime runtime, V8ValueObject o) throws JavetException {
        this.runtime = runtime;
        this.object = o;

        o.set("enablePromise", runtime.createV8ValuePromise());
        o.set("disablePromise", runtime.createV8ValuePromise());
    }


    @SneakyThrows
    @V8Function(thisObjectRequired = true)
    public V8ValuePromise whenEnabled(V8ValueObject o) {
        V8ValuePromise promise = o.get("enablePromise");
        return promise;
    }
    @SneakyThrows
    @V8Function(thisObjectRequired = true)
    public V8ValuePromise whenDisabled(V8ValueObject o) {
        V8ValuePromise promise = o.get("disablePromise");
        return promise;
    }

    public void triggerOnEnable() throws JavetException {
        V8ValuePromise promise = object.get("enablePromise");
        promise.resolve(runtime.createV8ValueUndefined());
    }
    public void triggerOnDisable() throws JavetException {
        V8ValuePromise promise = object.get("disablePromise");
        promise.resolve(runtime.createV8ValueUndefined());
    }

}
