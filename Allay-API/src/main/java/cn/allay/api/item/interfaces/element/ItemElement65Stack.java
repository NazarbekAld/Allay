package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement65Stack extends ItemStack {
  ItemType<ItemElement65Stack> ELEMENT_65_TYPE = ItemTypeBuilder
          .builder(ItemElement65Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_65)
          .build();
}