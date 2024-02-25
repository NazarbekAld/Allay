package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemMangroveDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemMangroveDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_DOUBLE_SLAB)
            .build();
  }
}