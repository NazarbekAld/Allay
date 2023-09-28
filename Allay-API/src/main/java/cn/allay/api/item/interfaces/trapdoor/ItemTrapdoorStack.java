package cn.allay.api.item.interfaces.trapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrapdoorStack extends ItemStack {
  ItemType<ItemTrapdoorStack> TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemTrapdoorStack.class)
          .vanillaItem(VanillaItemId.TRAPDOOR)
          .build();
}