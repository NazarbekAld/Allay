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
public interface BlockQuartzBlock extends Block {
    BlockType<BlockQuartzBlock> TYPE = BlockTypeBuilder
            .builder(BlockQuartzBlock.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK)
            .property(VanillaBlockPropertyTypes.CHISEL_TYPE,
                    VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}