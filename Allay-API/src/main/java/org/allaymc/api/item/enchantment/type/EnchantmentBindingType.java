package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentBindingType extends AbstractEnchantmentType {
    public static final EnchantmentBindingType BINDING_TYPE = new EnchantmentBindingType();
  private EnchantmentBindingType() {
    super(new Identifier("minecraft:binding"), 27, 1, Rarity.VERY_RARE);
  }
}