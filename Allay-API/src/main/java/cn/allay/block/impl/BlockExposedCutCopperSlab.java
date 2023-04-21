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
public interface BlockExposedCutCopperSlab extends Block {
    BlockType<BlockExposedCutCopperSlab> TYPE = BlockTypeBuilder
            .builder(BlockExposedCutCopperSlab.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_SLAB)
            .property(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}