package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueTerracottaStack extends ItemStack {
  ItemType<ItemLightBlueTerracottaStack> LIGHT_BLUE_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueTerracottaStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_TERRACOTTA)
          .build();
}