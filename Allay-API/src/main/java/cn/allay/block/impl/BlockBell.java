package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBell extends Block {
    BlockType<BlockBell> TYPE = BlockTypeBuilder
            .builder(BlockBell.class)
            .vanillaBlock(VanillaBlockId.BELL, true)
            .withProperties(VanillaBlockPropertyTypes.ATTACHMENT,
                    VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.TOGGLE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
