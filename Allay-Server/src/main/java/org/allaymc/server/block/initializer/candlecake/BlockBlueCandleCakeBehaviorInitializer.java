package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockBlueCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockBlueCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}