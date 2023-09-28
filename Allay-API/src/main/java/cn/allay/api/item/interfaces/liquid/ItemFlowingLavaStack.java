package cn.allay.api.item.interfaces.liquid;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowingLavaStack extends ItemStack {
  ItemType<ItemFlowingLavaStack> FLOWING_LAVA_TYPE = ItemTypeBuilder
          .builder(ItemFlowingLavaStack.class)
          .vanillaItem(VanillaItemId.FLOWING_LAVA)
          .build();
}