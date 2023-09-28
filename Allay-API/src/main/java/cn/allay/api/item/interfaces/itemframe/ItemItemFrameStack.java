package cn.allay.api.item.interfaces.itemframe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemFrameStack extends ItemStack {
    ItemType<ItemItemFrameStack> ITEM_FRAME_TYPE = ItemTypeBuilder
            .builder(ItemItemFrameStack.class)
            .vanillaItem(VanillaItemId.ITEM_FRAME)
            .build();
}