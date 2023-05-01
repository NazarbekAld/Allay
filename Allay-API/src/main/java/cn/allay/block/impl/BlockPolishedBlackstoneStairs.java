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
public interface BlockPolishedBlackstoneStairs extends Block {
    BlockType<BlockPolishedBlackstoneStairs> TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneStairs.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_STAIRS, true)
            .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT,
                    VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
