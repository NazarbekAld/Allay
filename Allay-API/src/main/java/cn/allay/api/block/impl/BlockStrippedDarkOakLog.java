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
public interface BlockStrippedDarkOakLog extends Block {
    BlockType<BlockStrippedDarkOakLog> TYPE = BlockTypeBuilder
            .builder(BlockStrippedDarkOakLog.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_DARK_OAK_LOG, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}