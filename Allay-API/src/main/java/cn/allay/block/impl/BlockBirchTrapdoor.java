package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchTrapdoor extends Block {
    BlockType<BlockBirchTrapdoor> TYPE = BlockTypeBuilder
            .builder(BlockBirchTrapdoor.class)
            .vanillaBlock(VanillaBlockId.BIRCH_TRAPDOOR)
            .property(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT,
                    VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
}