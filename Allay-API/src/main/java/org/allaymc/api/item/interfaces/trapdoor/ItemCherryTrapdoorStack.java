package org.allaymc.api.item.interfaces.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryTrapdoorStack extends ItemStack {
  ItemType<ItemCherryTrapdoorStack> CHERRY_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemCherryTrapdoorStack.class)
          .vanillaItem(VanillaItemId.CHERRY_TRAPDOOR)
          .build();
}