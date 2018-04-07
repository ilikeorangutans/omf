/*
 * Copyright 2012 Jakob Külzer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.om.core.impl.mapping.extractor;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.om.core.api.annotation.Entity;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Fields;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.core.impl.mapping.ImmutableFields;

public class EntityMappingExtractorImpl implements EntityMappingExtractor {
   @Override
   public EntityMapping extract(Class<?> type) throws MappingException {
      if (type == null) {
         throw new NullPointerException("Cannot extract entity mapping, type is null!");
      }
      final Entity annotation = type.getAnnotation(Entity.class);
      if (annotation == null) {
         throw new MappingException(type, "not annotated with @Entity");
      }
      final EntityMappingImpl result = new EntityMappingImpl(type, type.getName());
      final Fields mappedFields = extractFields(type);
      result.setFields(mappedFields);
      if (result.getIdProperty() == null) {
         throw new MappingException("Type " + type.getName() + " does not have an ID property. Annotate one property with @Id.");
      }
      return result;
   }

   public Fields extractFields(Class<?> type) {
      if (type == null) {
         throw new NullPointerException("Cannot extract entity mapping, type is null!");
      }
      final Entity entityAnnotation = type.getAnnotation(Entity.class);
      if (entityAnnotation == null) {
         throw new MappingException(type, "not annotated with @Entity, cannot extract property mapping");
      }
      final Set<MappedField> fields = new HashSet<MappedField>();
      final FieldMappingExtractorImpl fieldMappingExtractor = new FieldMappingExtractorImpl();
      for (final Field field : type.getDeclaredFields()) {
         try {
            final MappedField mappedField = fieldMappingExtractor.extract(field);
            if (mappedField != null) {
               fields.add(mappedField);
            }
         } catch (final MappingException e) {
            e.setMappedType(type);
            throw e;
         }
      }
      return new ImmutableFields(fields);
   }
}
