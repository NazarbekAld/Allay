package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHangingRoots extends Block {
    BlockType<BlockHangingRoots> TYPE = BlockTypeBuilder
            .builder(BlockHangingRoots.class)
            .vanillaBlock(VanillaBlockId.HANGING_ROOTS)
            .build();
}