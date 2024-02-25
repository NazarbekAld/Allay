package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSheafPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSheafPotterySherdStackInitializer {
  static void init() {
    ItemTypes.SHEAF_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemSheafPotterySherdStack.class)
            .vanillaItem(VanillaItemId.SHEAF_POTTERY_SHERD)
            .build();
  }
}