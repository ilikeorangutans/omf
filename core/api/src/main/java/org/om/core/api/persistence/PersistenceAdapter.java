package org.om.core.api.persistence;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.persistence.result.PersistenceResult;

/**
 * Implements actual access to properties through the respective persistence
 * layer.
 * 
 * @author Jakob Külzer
 * @author tom
 * 
 */
public interface PersistenceAdapter {

	/**
	 * Set the property described by the given {@link PropertyMapping}.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException;

	/**
	 * Retrieve the property described by the given {@link PropertyMapping}.
	 * This is used to retrieve single value entries.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	PersistenceResult getProperty(PropertyMapping mapping) throws ObjectMapperException;

	/**
	 * Retrieves the given collection. Implementations should return a
	 * collection that contains <b>all</b> identifiers for all elements in this
	 * collection.
	 * 
	 * The idea is that the actual persistence backend should be able to
	 * retrieve information like size of the collection or sequence of entries
	 * in a more performant way than the backend agnostic
	 * {@link PersistenceInterceptor} is able.
	 * 
	 * @param collectionMapping
	 * @return
	 */
	CollectionResult getCollection(CollectionMapping collectionMapping);

	/**
	 * delete a node
	 */
	void delete() throws ObjectMapperException;
}
