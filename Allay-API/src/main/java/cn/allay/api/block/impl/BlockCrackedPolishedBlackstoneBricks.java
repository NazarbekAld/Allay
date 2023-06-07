package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedPolishedBlackstoneBricks extends Block {
    BlockType<BlockCrackedPolishedBlackstoneBricks> TYPE = BlockTypeBuilder
            .builder(BlockCrackedPolishedBlackstoneBricks.class)
            .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS, true)
            .addBasicComponents()
            .build();
}