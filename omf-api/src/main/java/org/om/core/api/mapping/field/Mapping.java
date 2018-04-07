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
package org.om.core.api.mapping.field;

import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Mapped;

/**
 * Describes the persistence manager specific part of a @{@link Mapped} field.
 *
 * @author Jakob Külzer
 */
public interface Mapping {
   /**
    * Returns the declared type of this mapping. The declared type is used as the externally visible type of the mapped entities. This could be an interface that the actual entity implements.
    * <p>
    * If the declared type is not the entity type, {@link #getImplementationType()} will return it.
    * 
    * @return
    */
   Class<?> getDeclaredType();

   /**
    * Returns the implementation type of this mapped field. The implementation type is the class used to instantiate the entities described by this mapping.
    * 
    * @return
    */
   Class<?> getImplementationType();

   /**
    * Returns true if this mapping is an {@link Id} mapping.
    * 
    * @return
    */
   boolean isId();

   /**
    * Returns if the type of the property is either Java simple type (int, float, double, ...) or java.lang.String.
    * 
    * @return
    */
   boolean isPrimitiveOrWrappedType();
}
