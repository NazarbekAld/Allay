package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemDarkPrismarineStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkPrismarineStairsStackInitializer {
  static void init() {
    ItemTypes.DARK_PRISMARINE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemDarkPrismarineStairsStack.class)
            .vanillaItem(VanillaItemId.DARK_PRISMARINE_STAIRS)
            .build();
  }
}