package cn.allay.api.item.interfaces.concretepowder;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanConcretePowderStack extends ItemStack {
  ItemType<ItemCyanConcretePowderStack> CYAN_CONCRETE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemCyanConcretePowderStack.class)
          .vanillaItem(VanillaItemId.CYAN_CONCRETE_POWDER)
          .build();
}
