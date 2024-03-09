package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLureType extends AbstractEnchantmentType {
    public static final EnchantmentLureType LURE_TYPE = new EnchantmentLureType();
  private EnchantmentLureType() {
    super(new Identifier("minecraft:lure"), 24, 3, Rarity.RARE);
  }
}
