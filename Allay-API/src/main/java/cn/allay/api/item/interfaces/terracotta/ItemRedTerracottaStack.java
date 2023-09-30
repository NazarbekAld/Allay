package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedTerracottaStack extends ItemStack {
  ItemType<ItemRedTerracottaStack> RED_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemRedTerracottaStack.class)
          .vanillaItem(VanillaItemId.RED_TERRACOTTA)
          .build();
}
