package org.allaymc.api.block.interfaces.stairs;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.attribute.BlockAttributeComponentImpl;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.math.voxelshape.CommonShapes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakStairsBehavior extends BlockBehavior {
    BlockType<BlockDarkOakStairsBehavior> DARK_OAK_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .addComponent(BlockAttributeComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))

            .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
            .build();
}