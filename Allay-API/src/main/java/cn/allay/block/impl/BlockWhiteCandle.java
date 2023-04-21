package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteCandle extends Block {
    BlockType<BlockWhiteCandle> TYPE = BlockTypeBuilder
            .builder(BlockWhiteCandle.class)
            .vanillaBlock(VanillaBlockId.WHITE_CANDLE)
            .property(VanillaBlockPropertyTypes.CANDLES,
                    VanillaBlockPropertyTypes.LIT)
            .build();
}