package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLitSmokerBehavior extends BlockBehavior {
  BlockType<BlockLitSmokerBehavior> LIT_SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockLitSmokerBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_SMOKER)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .build();
}