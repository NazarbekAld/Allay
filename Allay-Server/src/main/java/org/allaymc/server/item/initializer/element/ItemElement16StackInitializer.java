package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement16Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement16StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_16_TYPE = ItemTypeBuilder
            .builder(ItemElement16Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_16)
            .build();
  }
}