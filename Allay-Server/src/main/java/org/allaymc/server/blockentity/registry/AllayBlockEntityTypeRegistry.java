package org.allaymc.server.blockentity.registry;

import org.allaymc.api.blockentity.registry.BlockEntityTypeRegistry;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.ReflectionUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayBlockEntityTypeRegistry extends SimpleMappedRegistry<String, BlockEntityType<?>, Map<String, BlockEntityType<?>>>  implements BlockEntityTypeRegistry {
    public AllayBlockEntityTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_BLOCKENTITYTYPE_LOADING));
        var classes = ReflectionUtils.getAllClasses("org.allaymc.api.blockentity.interfaces");
        classes.removeIf(clazz -> clazz.contains("Component"));
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Block Entity Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var entityClassName : classes) {
                Class.forName(entityClassName);
                pgbar.step();
            }
        }
        log.info(I18n.get().tr(TrKeys.A_BLOCKENTITYTYPE_LOADED, classes.size()));
    }
}
