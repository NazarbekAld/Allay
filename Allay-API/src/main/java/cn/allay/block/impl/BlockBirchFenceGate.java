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
public interface BlockBirchFenceGate extends Block {
    BlockType<BlockBirchFenceGate> TYPE = BlockTypeBuilder
            .builder(BlockBirchFenceGate.class)
            .vanillaBlock(VanillaBlockId.BIRCH_FENCE_GATE, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.IN_WALL_BIT,
                    VanillaBlockPropertyTypes.OPEN_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
