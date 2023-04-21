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
public interface BlockCrimsonStem extends Block {
    BlockType<BlockCrimsonStem> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonStem.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_STEM)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}