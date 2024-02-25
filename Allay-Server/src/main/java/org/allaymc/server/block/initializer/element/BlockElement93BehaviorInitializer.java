package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement93Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement93BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_93_TYPE = BlockTypeBuilder
            .builder(BlockElement93Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_93)
            .build();
  }
}