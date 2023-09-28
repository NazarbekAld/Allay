package cn.allay.api.item.interfaces.basalt;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBasaltStack extends ItemStack {
    ItemType<ItemBasaltStack> BASALT_TYPE = ItemTypeBuilder
            .builder(ItemBasaltStack.class)
            .vanillaItem(VanillaItemId.BASALT)
            .build();
}