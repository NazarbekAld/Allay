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
public interface BlockStructureVoid extends Block {
    BlockType<BlockStructureVoid> TYPE = BlockTypeBuilder
            .builder(BlockStructureVoid.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_VOID, true)
            .withProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
            .addBasicComponents()
            .build();
}