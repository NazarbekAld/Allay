package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockStoneBlockSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStoneBlockSlabBehaviorInitializer {
  static void init() {
    BlockTypes.STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE)
            .build();
  }
}