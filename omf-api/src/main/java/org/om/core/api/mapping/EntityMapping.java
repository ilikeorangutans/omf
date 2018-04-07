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
package org.om.core.api.mapping;

import org.om.core.api.annotation.Entity;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.api.mapping.field.PropertyMapping;

/**
 * Describes the mapping for an {@link Entity}.
 *
 * @author Jakob Külzer
 */
public interface EntityMapping {
   /**
    * Returns a {@link MappedField} by name.
    * 
    * @param name
    */
   MappedField getByFieldName(String name);

   /**
    * Returns the {@link PropertyMapping} for the identifier field.
    * 
    * @return
    */
   MappedField getIdProperty();

   Fields getMappedFields();

   /**
    * Returns the {@link Mapping} for the given field.
    * 
    * @param fieldname
    * @return
    */
   Mapping getMappingByField(String fieldname);

   /**
    * Returns the name of this entity.
    */
   String getName();

   /**
    * Returns the class of the mapped type.
    * 
    * @return
    */
   Class<?> getTypeClass();

   /**
    * Returns true if this entity has a {@link MappedField} with the given name.
    * 
    * @param field
    * @return
    */
   boolean hasField(String field);

   /**
    * Validates the mapping.
    */
   void validate() throws MappingException;
}
