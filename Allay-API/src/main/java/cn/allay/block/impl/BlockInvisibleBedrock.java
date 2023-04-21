package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockInvisibleBedrock extends Block {
    BlockType<BlockInvisibleBedrock> TYPE = BlockTypeBuilder
            .builder(BlockInvisibleBedrock.class)
            .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK)
            .build();
}