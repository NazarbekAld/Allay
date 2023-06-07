package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedBambooBlock extends Block {
    BlockType<BlockStrippedBambooBlock> TYPE = BlockTypeBuilder
            .builder(BlockStrippedBambooBlock.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BAMBOO_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}