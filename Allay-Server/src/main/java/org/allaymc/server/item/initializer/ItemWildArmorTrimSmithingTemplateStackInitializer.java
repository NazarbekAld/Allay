package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWildArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWildArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.WILD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemWildArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.WILD_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}