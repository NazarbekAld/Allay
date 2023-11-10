package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondAxeStack extends ItemStack {
  ItemType<ItemDiamondAxeStack> DIAMOND_AXE_TYPE = ItemTypeBuilder
          .builder(ItemDiamondAxeStack.class)
          .vanillaItem(VanillaItemId.DIAMOND_AXE)
          .build();
}