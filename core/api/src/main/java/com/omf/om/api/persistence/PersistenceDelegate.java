package com.omf.om.api.persistence;

import com.omf.om.api.mapping.PropertyMapping;

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
	 * 
	 * @param propertyMapping
	 * @return
	 */
	Object getProperty(PropertyMapping propertyMapping);

}
