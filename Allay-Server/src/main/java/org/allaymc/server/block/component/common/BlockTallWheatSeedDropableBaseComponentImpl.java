package org.allaymc.server.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * Allay Project 2024/6/18
 * <p>
 * 适用于两格高的可掉落小麦种子的植物
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "upper_block_bit")
public class BlockTallWheatSeedDropableBaseComponentImpl extends BlockWheatSeedDropableBaseComponentImpl {
    public BlockTallWheatSeedDropableBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face != BlockFace.UP && face != BlockFace.DOWN) return;
        var dimension = current.pos().dimension();
        var isUpperBlock = current.blockState().getPropertyValue(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT);
        var willBreak = false;
        if (isUpperBlock) {
            willBreak = notSamePlant(dimension.getBlockState(BlockFace.DOWN.offsetPos(current.pos())));
        } else {
            willBreak = notSamePlant(dimension.getBlockState(BlockFace.UP.offsetPos(current.pos())));
        }
        if (willBreak) dimension.breakBlock(current.pos(), null, null);
    }

    protected boolean notSamePlant(BlockState downBlock) {
        return downBlock.getBlockType() != blockType;
    }
}
