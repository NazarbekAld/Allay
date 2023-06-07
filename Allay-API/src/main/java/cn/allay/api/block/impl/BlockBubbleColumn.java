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
public interface BlockBubbleColumn extends Block {
    BlockType<BlockBubbleColumn> TYPE = BlockTypeBuilder
            .builder(BlockBubbleColumn.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN, true)
            .withProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
            .addBasicComponents()
            .build();
}