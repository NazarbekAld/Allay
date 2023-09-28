package cn.allay.api.block.interfaces.grasspath;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrassPathBehavior extends BlockBehavior {
    BlockType<BlockGrassPathBehavior> GRASS_PATH_TYPE = BlockTypeBuilder
            .builder(BlockGrassPathBehavior.class)
            .vanillaBlock(VanillaBlockId.GRASS_PATH)
            .build();
}