package cn.allay.api.item.interfaces.concretepowder;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayConcretePowderStack extends ItemStack {
  ItemType<ItemLightGrayConcretePowderStack> LIGHT_GRAY_CONCRETE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemLightGrayConcretePowderStack.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_CONCRETE_POWDER)
          .build();
}
