package org.om.core.impl.entity;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

@Entity
public class MyInterfaceImpl implements MyInterface {
   @Id
   private String id;
   @Property
   private String value;

   public MyInterfaceImpl() {
   }

   public String getId() {
      return id;
   }

   @Override
   public String getValue() {
      return value;
   }
}
