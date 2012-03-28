package org.om.core.api.persistence;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.PropertyMapping;

/**
 * A persistence delegate implements actual access to properties through the
 * respective persistence layer.
 * 
 * @author Jakob Külzer
 * @author tom
 * 
 */
public interface PersistenceDelegate {

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
	Object getProperty(Mapping mapping) throws ObjectMapperException;

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
}
