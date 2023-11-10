package org.allaymc.api.datastruct.aabbtree;

import java.util.Objects;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
public record CollisionPair<T extends HasAABB & HasLongId>(T objectA, T objectB) {

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      CollisionPair<?> that = (CollisionPair<?>) o;
      return (Objects.equals(objectA.getUniqueId(), that.objectA.getUniqueId()) || Objects.equals(objectA.getUniqueId(), that.objectB.getUniqueId())) &&
             (Objects.equals(objectB.getUniqueId(), that.objectB.getUniqueId()) || Objects.equals(objectB.getUniqueId(), that.objectA.getUniqueId()));
   }

   @Override
   public int hashCode() {
      return Objects.hash(objectA.getUniqueId(), objectB.getUniqueId()) ^ Objects.hash(objectB.getUniqueId(), objectA.getUniqueId());
   }
}