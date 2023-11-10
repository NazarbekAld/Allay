package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemMangroveDoorStack extends ItemStack {
  ItemType<ItemItemMangroveDoorStack> ITEM_MANGROVE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemMangroveDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_MANGROVE_DOOR)
          .build();
}