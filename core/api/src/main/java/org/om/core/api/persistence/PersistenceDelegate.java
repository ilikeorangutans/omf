package org.om.core.api.persistence;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;

/**
 * A persistence delegate implements actual access to properties through the
 * respective persistence layer.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PersistenceDelegate {

	/**
	 * Retrieve the property described by the given {@link PropertyMapping}.
	 * This is used to retrieve single value entries.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	Object getProperty(Mapping mapping);

	/**
	 * Returns true if the delegate can provide a value for the given mapping.
	 * If the underlying storage engine returns a null or cannot resolve the
	 * implementation specific property, this method should return false.
	 * 
	 * @return true if the delegate can provide a value for the given property
	 */
	boolean canProvide(Mapping mapping);
}
