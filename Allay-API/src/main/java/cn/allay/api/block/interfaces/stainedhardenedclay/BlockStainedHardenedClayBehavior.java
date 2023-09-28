package cn.allay.api.block.interfaces.stainedhardenedclay;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStainedHardenedClayBehavior extends BlockBehavior {
    BlockType<BlockStainedHardenedClayBehavior> STAINED_HARDENED_CLAY_TYPE = BlockTypeBuilder
            .builder(BlockStainedHardenedClayBehavior.class)
            .vanillaBlock(VanillaBlockId.STAINED_HARDENED_CLAY)
            .setProperties(VanillaBlockPropertyTypes.COLOR)
            .build();
}