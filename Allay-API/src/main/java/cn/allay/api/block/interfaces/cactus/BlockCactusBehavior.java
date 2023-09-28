package cn.allay.api.block.interfaces.cactus;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCactusBehavior extends BlockBehavior {
    BlockType<BlockCactusBehavior> CACTUS_TYPE = BlockTypeBuilder
            .builder(BlockCactusBehavior.class)
            .vanillaBlock(VanillaBlockId.CACTUS)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
}