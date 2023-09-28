package cn.allay.api.block.interfaces.goldblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGoldBlockBehavior extends BlockBehavior {
    BlockType<BlockGoldBlockBehavior> GOLD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockGoldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLD_BLOCK)
            .build();
}