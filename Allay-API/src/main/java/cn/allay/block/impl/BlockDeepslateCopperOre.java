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
public interface BlockDeepslateCopperOre extends Block {
    BlockType<BlockDeepslateCopperOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateCopperOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
