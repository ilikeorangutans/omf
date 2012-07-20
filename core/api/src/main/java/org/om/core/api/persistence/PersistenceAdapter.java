package org.om.core.api.persistence;

import java.util.Collection;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;

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
	 * Returns true if the delegate can provide a value for the given mapping.
	 * If the underlying storage engine returns a null or cannot resolve the
	 * implementation specific property, this method should return false.
	 * 
	 * @return true if the delegate can provide a value for the given property
	 */
	boolean canProvide(Mapping mapping) throws ObjectMapperException;

	/**
	 * delete a node
	 */
	void delete() throws ObjectMapperException;

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
	Collection<?> getCollection(CollectionMapping collectionMapping);

	/**
	 * Retrieve the property described by the given {@link PropertyMapping}.
	 * This is used to retrieve single value entries.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	Object getProperty(PropertyMapping mapping) throws ObjectMapperException;

	/**
	 * Set the property described by the given {@link PropertyMapping}.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException;
}
