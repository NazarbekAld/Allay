package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackTerracottaStack extends ItemStack {
  ItemType<ItemBlackTerracottaStack> BLACK_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemBlackTerracottaStack.class)
          .vanillaItem(VanillaItemId.BLACK_TERRACOTTA)
          .build();
}
