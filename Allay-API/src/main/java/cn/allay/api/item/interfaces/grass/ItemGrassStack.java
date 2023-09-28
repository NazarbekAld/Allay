package cn.allay.api.item.interfaces.grass;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrassStack extends ItemStack {
    ItemType<ItemGrassStack> GRASS_TYPE = ItemTypeBuilder
            .builder(ItemGrassStack.class)
            .vanillaItem(VanillaItemId.GRASS)
            .build();
}