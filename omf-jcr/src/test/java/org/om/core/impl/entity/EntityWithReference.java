package org.om.core.impl.entity;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Mapped;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.annotation.Property;

@Entity
public class EntityWithReference {
   @Id
   private String id;
   @Property
   private TestEntity testEntity;
   @Mapped(missingStrategy = MissingStrategy.ThrowException)
   @Property
   private TestEntity referenceThrowingExceptionOnMissing;

   public EntityWithReference() {
   }

   public String getId() {
      return id;
   }

   public TestEntity getReferenceThrowingExceptionOnMissing() {
      return referenceThrowingExceptionOnMissing;
   }

   public TestEntity getTestEntity() {
      return testEntity;
   }
}
