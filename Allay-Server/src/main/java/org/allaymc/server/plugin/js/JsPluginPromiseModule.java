package org.allaymc.server.plugin.js;

import com.caoccao.javet.annotations.V8Function;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.values.reference.V8ValueObject;
import com.caoccao.javet.values.reference.V8ValuePromise;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
        return o.get("enablePromise");
    }
    @SneakyThrows
    @V8Function(thisObjectRequired = true)
    public V8ValuePromise whenDisabled(V8ValueObject o) {
        return o.get("disablePromise");
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
