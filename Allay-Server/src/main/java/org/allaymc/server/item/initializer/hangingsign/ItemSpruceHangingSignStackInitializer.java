package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemSpruceHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceHangingSignStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemSpruceHangingSignStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_HANGING_SIGN)
            .build();
  }
}