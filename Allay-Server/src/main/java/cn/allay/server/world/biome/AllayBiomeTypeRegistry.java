package cn.allay.server.world.biome;

import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.world.biome.BiomeData;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.MutableNbtMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class AllayBiomeTypeRegistry extends SimpleMappedRegistry<BiomeType, BiomeData, Map<BiomeType, BiomeData>> implements BiomeTypeRegistry {

    private MutableNbtMap biomeDefinition;

    public AllayBiomeTypeRegistry() {
        super(null, input -> new HashMap<>());
        loadVanillaBiomeDefinition();
    }

    @SneakyThrows
    private void loadVanillaBiomeDefinition() {
        try (InputStream stream = AllayBiomeTypeRegistry.class.getClassLoader().getResourceAsStream("biome_definitions.nbt")) {
            assert stream != null;
            biomeDefinition = MutableNbtMap.from((NbtMap) NbtUtils.createNetworkReader(stream).readTag());
            int i = 0;
            for (var biome : biomeDefinition.entrySet()) {
                var type = VanillaBiomeId.values()[i];
                NbtMap value = (NbtMap) biome.getValue();
                BiomeData data = NbtUtils.createRecordFromNBT(BiomeData.class, value);
                super.register(type, data);
                i++;
            }
        }
    }

    @Override
    public BiomeData register(BiomeType biomeType, BiomeData biomeData) {
        biomeDefinition.putCompound(biomeType.getName(), NbtMap.fromRecord(biomeData));
        return super.register(biomeType, biomeData);
    }

    @Override
    public NbtMap getBiomeDefinition() {
        return biomeDefinition.build();
    }
}