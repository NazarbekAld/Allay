package cn.allay.api.block.interfaces.grindstone;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrindstoneBehavior extends BlockBehavior {
    BlockType<BlockGrindstoneBehavior> GRINDSTONE_TYPE = BlockTypeBuilder
            .builder(BlockGrindstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.GRINDSTONE)
            .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION)
            .build();
}