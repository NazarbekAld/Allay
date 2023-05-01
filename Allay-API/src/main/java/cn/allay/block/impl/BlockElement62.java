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
public interface BlockElement62 extends Block {
    BlockType<BlockElement62> TYPE = BlockTypeBuilder
            .builder(BlockElement62.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_62, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
