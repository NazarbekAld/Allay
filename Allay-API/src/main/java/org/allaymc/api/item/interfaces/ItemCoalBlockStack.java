package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalBlockStack extends ItemStack {
  ItemType<ItemCoalBlockStack> COAL_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemCoalBlockStack.class)
          .vanillaItem(VanillaItemId.COAL_BLOCK)
          .build();
}