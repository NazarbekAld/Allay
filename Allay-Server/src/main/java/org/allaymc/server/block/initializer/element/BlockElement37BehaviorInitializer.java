package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement37Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement37BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_37_TYPE = BlockTypeBuilder
            .builder(BlockElement37Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_37)
            .build();
  }
}