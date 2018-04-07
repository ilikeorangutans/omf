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
package org.om.core.api.persistence.proxy;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;

/**
 * Creates proxy instances for entities.
 *
 * @author Jakob Külzer
 */
public interface ProxyFactory {
   /**
    * Create a proxy object for the given {@link Session} and {@link EntityMapping}. The returned object will have the same type or supertype as described by the {@link EntityMapping}. The returned
    * proxy object will be bound to the given session.
    * 
    * @param persistenceDelegate The {@link PersistenceAdapter} the created proxy will use to access the persistence backend.
    * @return A proxy object for the given type.
    */
   Object create(Session session, EntityMapping entityMapping, PersistenceAdapter persistenceDelegate);
}
