package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement95Behavior extends BlockBehavior {
  BlockType<BlockElement95Behavior> ELEMENT_95_TYPE = BlockTypeBuilder
          .builder(BlockElement95Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_95)
          .build();
}