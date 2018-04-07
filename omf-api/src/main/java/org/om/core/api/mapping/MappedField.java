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

import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.mapping.field.Mapping;

/**
 * Describes the field on a mapped entity.
 *
 * @author Jakob Külzer
 */
public interface MappedField {
   /**
    * Return the actual mapping.
    * 
    * @return
    */
   Mapping getMapping();

   /**
    * Exception to throw when {@link #getMissingStrategy()} is set to {@link MissingStrategy#ThrowException} and the property cannot be found.
    * 
    * @return
    */
   Class<RuntimeException> getMissingException();

   /**
    * Strategy to be used when the given property cannot be found in the persistence layer.
    * 
    * @return
    */
   MissingStrategy getMissingStrategy();

   /**
    * Returns the name of the field.
    * 
    * @return
    */
   String getName();

   /**
    * Returns the declared type of the field.
    * 
    * @return
    */
   Class<?> getType();
}
