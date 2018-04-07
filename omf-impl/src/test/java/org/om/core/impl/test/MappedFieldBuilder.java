package org.om.core.impl.test;

import org.om.core.api.annotation.CollectionMode;
import org.om.core.api.annotation.LookupMode;
import org.om.core.api.annotation.MapKeyStrategy;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.exception.MissingException;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.impl.mapping.ImmutableCollectionMapping;
import org.om.core.impl.mapping.ImmutableMappedField;
import org.om.core.impl.mapping.field.ImmutableIdMapping;
import org.om.core.impl.mapping.field.ImmutablePropertyMapping;
import org.om.core.impl.mapping.field.ImmutableReferenceMapping;

/**
 * Helper utility class to build {@link MappedField} instances.
 *
 * @author Jakob Külzer
 */
public class MappedFieldBuilder {
   private String name;
   private Class<?> type;
   private Mapping mapping;
   private MissingStrategy missingStrategy = MissingStrategy.ReturnNull;
   private Class<? extends RuntimeException> missingException = MissingException.class;

   /**
    * Creates a new instance of {@link MappedField} using the previously set parameters.
    * 
    * @return
    */
   public MappedField create() {
      if (name == null) {
         throw new IllegalStateException("name has not been set.");
      }
      if (type == null) {
         throw new IllegalStateException("type has not been set.");
      }
      if (mapping == null) {
         throw new IllegalStateException("mapping has not been set.");
      }
      return new ImmutableMappedField(name, type, mapping, missingStrategy, missingException);
   }

   public MappedFieldBuilder withCollectionMapping(Class<?> fieldType, Class<?> targetType, Class<?> implementationType, String location) {
      withCollectionMapping(fieldType, targetType, implementationType, location, CollectionMode.Children, MapKeyStrategy.Name);
      return this;
   }

   public MappedFieldBuilder withCollectionMapping(Class<?> fieldType, Class<?> targetType, Class<?> implementationType, String location, CollectionMode collectionMode,
         MapKeyStrategy mapKeyStrategy) {
      mapping = new ImmutableCollectionMapping(fieldType, targetType, implementationType, location, collectionMode, mapKeyStrategy);
      return this;
   }

   public MappedFieldBuilder withCollectionMapping(Class<?> fieldType, Class<?> targetType, String location) {
      withCollectionMapping(fieldType, targetType, targetType, location, CollectionMode.Children, MapKeyStrategy.Name);
      return this;
   }

   public MappedFieldBuilder withIdMapping() {
      mapping = new ImmutableIdMapping();
      return this;
   }

   public MappedFieldBuilder withMissingException(Class<? extends RuntimeException> exception) {
      missingException = exception;
      return this;
   }

   public MappedFieldBuilder withMissingStrategy(MissingStrategy missingStrategy) {
      this.missingStrategy = missingStrategy;
      return this;
   }

   public MappedFieldBuilder withName(String name) {
      this.name = name;
      return this;
   }

   public MappedFieldBuilder withPropertyMapping(String propertyName, Class<?> propertyType) {
      withPropertyMapping(propertyName, propertyType, null);
      return this;
   }

   public MappedFieldBuilder withPropertyMapping(String propertyName, Class<?> propertyType, String defaultValue) {
      mapping = new ImmutablePropertyMapping(propertyName, propertyType, defaultValue);
      return this;
   }

   public MappedFieldBuilder withReferenceMapping(String propertyName, Class<?> declaredType, Class<?> implementationType, String path, LookupMode lookupMode) {
      mapping = new ImmutableReferenceMapping(declaredType, implementationType, path, lookupMode);
      return this;
   }

   public MappedFieldBuilder withReferenceMapping(String propertyName, Class<?> declaredType, String path) {
      mapping = new ImmutableReferenceMapping(declaredType, declaredType, path, LookupMode.Reference);
      return this;
   }

   public MappedFieldBuilder withType(Class<?> type) {
      this.type = type;
      return this;
   }
}
