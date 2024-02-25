package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStoneAxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneAxeStackInitializer {
  static void init() {
    ItemTypes.STONE_AXE_TYPE = ItemTypeBuilder
            .builder(ItemStoneAxeStack.class)
            .vanillaItem(VanillaItemId.STONE_AXE)
            .build();
  }
}