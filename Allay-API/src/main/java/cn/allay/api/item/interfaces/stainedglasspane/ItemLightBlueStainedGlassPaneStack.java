package cn.allay.api.item.interfaces.stainedglasspane;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueStainedGlassPaneStack extends ItemStack {
  ItemType<ItemLightBlueStainedGlassPaneStack> LIGHT_BLUE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_STAINED_GLASS_PANE)
          .build();
}
