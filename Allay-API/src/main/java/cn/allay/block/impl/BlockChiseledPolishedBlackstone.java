package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledPolishedBlackstone extends Block {
    BlockType<BlockChiseledPolishedBlackstone> TYPE = BlockTypeBuilder
            .builder(BlockChiseledPolishedBlackstone.class)
            .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
