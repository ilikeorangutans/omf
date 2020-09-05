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
package org.om.core.api.persistence;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.interceptor.*;
import org.om.core.api.persistence.request.*;
import org.om.core.api.persistence.result.*;

/**
 * Implements actual access to properties through the respective persistence layer.
 *
 * @author Jakob Külzer
 * @author tom
 */
public interface PersistenceAdapter {
	/**
	 * delete a node
	 */
	void delete() throws ObjectMapperException;

	/**
	 * Retrieves the given collection. Implementations should return a collection that contains
	 * <b>all</b> identifiers for all elements in this collection. The idea is that the actual
	 * persistence backend should be able to retrieve information like size of the collection or
	 * sequence of entries in a more performant way than the backend agnostic
	 * {@link PersistenceInterceptor} can.
	 * 
	 * @param collectionMapping
	 * @return
	 */
	CollectionResult getCollection(CollectionMapping collectionMapping);

	/**
	 * Returns the id that was used to initialize this adapter.
	 * 
	 * @return
	 */
	String getId();

	/**
	 * Retrieves a map using the given mapping.
	 * 
	 * @param collectionMapping
	 * @return
	 */
	MapResult getMapResult(CollectionMapping collectionMapping);

	/**
	 * Retrieves a scalar value from the underlying persistence layer.
	 * 
	 * @param request
	 * @return a result object, never null.
	 * @throws PersistenceLayerException if a unrecoverable error occurs on the persistence layer.
	 */
	PersistenceResult getProperty(PersistenceRequest request) throws PersistenceLayerException;

	/**
	 * Retrieve the property described by the given {@link PropertyMapping}. This is used to retrieve
	 * single value entries.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	@Deprecated
	PersistenceResult getProperty(PropertyMapping mapping) throws ObjectMapperException;

	/**
	 * Resolves the given id relative to this adapter.
	 * 
	 * @param id
	 * @return
	 */
	Object resolve(String id);

	/**
	 * Set the property described by the given {@link PropertyMapping}.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException;
}
