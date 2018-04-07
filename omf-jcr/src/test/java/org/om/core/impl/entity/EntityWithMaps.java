package org.om.core.impl.entity;

import java.util.Map;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.CollectionMode;
import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;

@Entity
public class EntityWithMaps {
   @Id
   private String id;
   @Collection(mode = CollectionMode.Properties, location = "mapFromProperties", targetType = String.class)
   private Map<String, String> map;

   public EntityWithMaps() {
   }

   public String getId() {
      return id;
   }

   public Map<String, String> getMap() {
      return map;
   }
}
