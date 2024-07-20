package org.allaymc.server.plugin.js;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginLoadOrder;

import java.util.List;

@Getter
public class PackageJsonDescriptor implements PluginDescriptor {

    private String name;
    @SerializedName("main")
    private String entrance;
    private String author = "";
    private String description = "";
    private String version = "1.0.0";
    @SerializedName("pluginDependencies")
    private List<PluginDependency> dependencies = List.of();
    private String website = "";

    @Override
    public PluginLoadOrder getOrder() {
        return PluginLoadOrder.START_UP;
    }

    @Override
    public List<String> getAuthors() {
        return List.of(author);
    }
}
