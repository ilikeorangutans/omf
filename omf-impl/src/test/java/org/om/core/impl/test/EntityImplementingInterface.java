package org.om.core.impl.test;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Property;

@Entity
public class EntityImplementingInterface implements MyInterface {
   @Property
   private String value;

   public EntityImplementingInterface() {
   }

   @Override
   public String getValue() {
      return null;
   }
}
