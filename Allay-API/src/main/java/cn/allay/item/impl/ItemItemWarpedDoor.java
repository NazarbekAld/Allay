package cn.allay.item.impl;

import cn.allay.item.ItemStack;
import cn.allay.item.data.VanillaItemId;
import cn.allay.item.type.ItemType;
import cn.allay.item.type.ItemTypeBuilder;
import cn.allay.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWarpedDoor extends ItemStack {
    ItemType<ItemItemWarpedDoor> TYPE = ItemTypeBuilder
            .builder(ItemItemWarpedDoor.class)
            .vanillaItem(VanillaItemId.ITEM_WARPED_DOOR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
