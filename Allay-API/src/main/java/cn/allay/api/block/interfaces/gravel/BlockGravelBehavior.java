package cn.allay.api.block.interfaces.gravel;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGravelBehavior extends BlockBehavior {
    BlockType<BlockGravelBehavior> GRAVEL_TYPE = BlockTypeBuilder
            .builder(BlockGravelBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAVEL)
            .build();
}