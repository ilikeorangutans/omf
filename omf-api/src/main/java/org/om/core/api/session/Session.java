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
package org.om.core.api.session;

import org.om.core.api.exception.MappingException;
import org.om.core.api.exception.ObjectMapperException;

/**
 * A single persistence session. A session acts as factory for entities. Sessions are not threadsafe, they are meant to be used by a single thread only.
 *
 * @author Jakob Külzer
 * @author tom
 */
public interface Session {
   /**
    * Closes the session. TODO: What does this actually mean? Will the session not be able to produce further objects? Will it close the underlying persistence layer? We need to define the semantics.
    */
   void close() throws ObjectMapperException;

   /**
    * Asks the session to write all pending changes to the persistence layer.
    */
   void commit();

   /**
    * Deletes the given object. If the given object is not a mapped and persisted entity, this method will throw an error. This call will ask the underlying persistence layer to remove the
    * representation of this particular instance. TODO: This is a modifying operation, so the implications of transaction handling have to be declared.
    */
   void delete(Object o) throws ObjectMapperException;

   /**
    * get an object by id
    */
   <T> T get(Class<T> clazz, Object id) throws ObjectMapperException;

   /**
    * Saves the given instance. If the class describing the object is not a mapped entity, this method will throw an exception. TODO: This is a modifying operation, so the implications of transaction
    * handling have to be declared.
    * 
    * @throws MappingException
    */
   void save(Object o) throws ObjectMapperException, MappingException;
}
