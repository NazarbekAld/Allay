package cn.allay.api.item.interfaces.flowerpot;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowerPotStack extends ItemStack {
    ItemType<ItemFlowerPotStack> FLOWER_POT_TYPE = ItemTypeBuilder
            .builder(ItemFlowerPotStack.class)
            .vanillaItem(VanillaItemId.FLOWER_POT)
            .build();
}