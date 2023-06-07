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
public interface BlockBirchPressurePlate extends Block {
  BlockType<BlockBirchPressurePlate> TYPE = BlockTypeBuilder
          .builder(BlockBirchPressurePlate.class)
          .vanillaBlock(VanillaBlockId.BIRCH_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();
}