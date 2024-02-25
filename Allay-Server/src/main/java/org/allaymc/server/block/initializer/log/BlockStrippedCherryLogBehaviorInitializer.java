package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockStrippedCherryLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedCherryLogBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_CHERRY_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCherryLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}