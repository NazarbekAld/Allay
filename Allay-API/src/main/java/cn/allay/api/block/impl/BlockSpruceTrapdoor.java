package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSpruceTrapdoor extends Block {
    BlockType<BlockSpruceTrapdoor> TYPE = BlockTypeBuilder
            .builder(BlockSpruceTrapdoor.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_TRAPDOOR, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.OPEN_BIT,
                    VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .addBasicComponents()
            .build();
}