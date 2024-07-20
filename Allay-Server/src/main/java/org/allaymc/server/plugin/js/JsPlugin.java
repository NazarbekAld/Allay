package org.allaymc.server.plugin.js;

import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.callback.JavetBuiltInModuleResolver;
import com.caoccao.javet.interop.converters.JavetProxyConverter;
import com.caoccao.javet.values.reference.V8ValueObject;
import io.mvnpm.esbuild.Bundler;
import io.mvnpm.esbuild.model.BundleOptions;
import io.mvnpm.esbuild.model.EsBuildConfig;
import lombok.SneakyThrows;
import org.allaymc.api.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class JsPlugin extends Plugin {
    protected Logger logger = null;
    private final Path workingDirectory;
    private JsPluginPromiseModule pluginPromiseModule;
    private V8Runtime runtime;

    public JsPlugin(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @SneakyThrows
    @Override
    public void onLoad() {
        logger = LoggerFactory.getLogger(getPluginContainer().descriptor().getName());
        runtime = V8Host.getNodeInstance().createV8Runtime();
        runtime.setLogger(new JavetLoggerAdapter(logger));
        Path bundledScript = Bundler.bundle(
                BundleOptions.builder()
                        .withWorkDir(workingDirectory.toAbsolutePath())
                        .addEntryPoint(getPluginContainer().descriptor().getEntrance())
                        .withNodeModulesDir(workingDirectory.resolve("node_modules").toAbsolutePath())
                        .withEsConfig(
                                EsBuildConfig.builder()
                                        .entryNames("[dir]/[name]")
                                        .outDir("bundleResult")
                                        .platform(EsBuildConfig.Platform.NODE)
                                        .minify(true)
                                        .build()
                        )
                        .build()
                , false
        ).dist().resolve(getPluginContainer().descriptor().getEntrance().split("\\.")[0] + ".js");
        runtime.setConverter(new JavetProxyConverter());
        runtime.setV8ModuleResolver(new JavetBuiltInModuleResolver());
        runtime.getGlobalObject().set("plugin", this);

        V8ValueObject o = runtime.createV8ValueObject();
        this.pluginPromiseModule = new JsPluginPromiseModule(runtime, o);
        o.bind(pluginPromiseModule);
        runtime.getGlobalObject().set("pluginState", o);
        // Evaluation
        runtime.getExecutor(bundledScript)
                .setModule(true)
                .executeVoid();
        runtime.await();

    }

    @SneakyThrows
    @Override
    public void onEnable() {
        pluginPromiseModule.triggerOnEnable();
        runtime.await();
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        pluginPromiseModule.triggerOnDisable();
        runtime.await();
    }

    @SneakyThrows
    @Override
    public void onUnload() {
        runtime.resetIsolate();
        runtime.lowMemoryNotification();
        runtime.close();
    }

    @Override
    public boolean isReloadable() {
        return true;
    }

    @Override
    public void reload() {
        onDisable();
        onUnload();
        onLoad();
        onEnable();
    }

}
