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
public interface BlockPistonArmCollision extends Block {
    BlockType<BlockPistonArmCollision> TYPE = BlockTypeBuilder
            .builder(BlockPistonArmCollision.class)
            .vanillaBlock(VanillaBlockId.PISTON_ARM_COLLISION, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
