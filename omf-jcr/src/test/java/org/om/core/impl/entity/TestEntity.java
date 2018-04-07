package org.om.core.impl.entity;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Mapped;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.annotation.Property;

@Entity
public class TestEntity {
   @Mapped(missingStrategy = MissingStrategy.DefaultValue)
   @Property(defaultValue = "3131", name = "mycoolfield")
   private int blargh;
   @Property
   private String foobar;
   @Id
   private String id;

   public TestEntity() {
   }

   public int getBlargh() {
      return blargh;
   }

   public String getFoobar() {
      return foobar;
   }

   public String getId() {
      return id;
   }

   public void setBlargh(int blargh) {
      this.blargh = blargh;
   }

   public void setFoobar(String foobar) {
      this.foobar = foobar;
   }

   public void setId(String id) {
      this.id = id;
   }
}
