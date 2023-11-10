package org.allaymc.api.entity.interfaces.tadpole;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTadpole extends Entity {
  EntityType<EntityTadpole> TADPOLE_TYPE = EntityTypeBuilder
          .builder(EntityTadpole.class)
          .vanillaEntity(VanillaEntityId.TADPOLE)
          .build();
}