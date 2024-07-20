package org.allaymc.server.plugin.js;

import com.caoccao.javet.enums.JSRuntimeType;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.engine.JavetEngineConfig;
import com.caoccao.javet.interop.engine.JavetEnginePool;
import com.caoccao.javet.interop.options.RuntimeOptions;
import com.caoccao.javet.values.reference.V8ValueObject;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18nLoader;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginLoader;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.i18n.AllayI18n;
import org.allaymc.server.plugin.SimplePluginDescriptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.allaymc.api.plugin.PluginContainer.createPluginContainer;
import static org.allaymc.server.plugin.DefaultPluginSource.getOrCreateDataFolder;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
@Slf4j
public class JsPluginLoader implements PluginLoader {

    @Getter
    protected Path pluginPath;
    protected PackageJsonDescriptor descriptor;

    @SneakyThrows
    public JsPluginLoader(Path pluginPath) {
        this.pluginPath = pluginPath;
    }

    @SneakyThrows
    @Override
    public PluginDescriptor loadDescriptor() {
        descriptor = JSONUtils.from(Files.newBufferedReader(pluginPath.resolve("package.json")), PackageJsonDescriptor.class);
        return descriptor;
    }

    @SneakyThrows
    @Override
    public PluginContainer loadPlugin() {
        return createPluginContainer(
                new JsPlugin(pluginPath),
                descriptor, this,
                getOrCreateDataFolder(descriptor.getName()),
                new AllayI18n(new JsPluginI18nLoader(), Server.SETTINGS.genericSettings().language())
        );
    }

    public static class JsPluginLoaderFactory implements PluginLoaderFactory {

        @Override
        public boolean canLoad(Path pluginPath) {
            return pluginPath.getFileName().toString().startsWith("@") && Files.isDirectory(pluginPath);
        }

        @Override
        public PluginLoader create(Path pluginPath) {
            return new JsPluginLoader(pluginPath);
        }
    }

    public class JsPluginI18nLoader implements I18nLoader {
        @Override
        public Map<String, String> getLangMap(LangCode langCode) {
            try {
                var str = Files.readString(pluginPath.resolve("lang").resolve(langCode.name() + ".json"));
                TypeToken<HashMap<String, String>> typeToken = new TypeToken<>() {};
                return JSONUtils.fromLenient(str, typeToken);
            } catch (NoSuchFileException e) {
                return Collections.emptyMap();
            } catch (IOException e) {
                log.error("Error while loading plugin language file", e);
                return Collections.emptyMap();
            }
        }
    }
}
