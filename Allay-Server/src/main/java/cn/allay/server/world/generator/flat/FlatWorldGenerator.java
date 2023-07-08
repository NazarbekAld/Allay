package cn.allay.server.world.generator.flat;

import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldType;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.generator.LimitedWorldRegion;
import cn.allay.api.world.generator.WorldGenerator;
import cn.allay.server.world.chunk.AllayChunk;

/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
public class FlatWorldGenerator implements WorldGenerator {
    @Override
    public void generate(LimitedWorldRegion region) {
        for (int cx = region.minChunkX(); cx <= region.maxChunkX(); cx++) {
            for (int cz = region.minChunkZ(); cz <= region.maxChunkZ(); cz++) {
                region.setChunk(cx, cz, createFlatChunk(cx, cz));
            }
        }
    }

    private Chunk createFlatChunk(int cx, int cz) {
        var flatChunk = new AllayChunk(cx, cz, DimensionInfo.OVERWORLD);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 5; y++) {
                    if (y == 0) {
                        flatChunk.setBlock(x, y, z, VanillaBlockTypes.BEDROCK_TYPE.getDefaultState());
                    } else flatChunk.setBlock(x, y, z, VanillaBlockTypes.GRASS_TYPE.getDefaultState());
                }
            }
        }
        return flatChunk;
    }

    @Override
    public WorldType getGeneratorWorldType() {
        return WorldType.FLAT;
    }
}
