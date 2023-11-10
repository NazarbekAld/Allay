package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSheafPotterySherdStack extends ItemStack {
  ItemType<ItemSheafPotterySherdStack> SHEAF_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemSheafPotterySherdStack.class)
          .vanillaItem(VanillaItemId.SHEAF_POTTERY_SHERD)
          .build();
}