package com.omf.om.api.persistence;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.session.Session;

/**
 * A persistence delegate implements actual access to properties through the
 * respective persistence layer.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PersistenceDelegate {

	/**
	 * Returns the {@link EntityMapping} for this delegate.
	 * 
	 * @return
	 */
	EntityMapping getEntityMapping();

	/**
	 * Returns the associated session.
	 * 
	 * @return
	 */
	Session getSession();

	/**
	 * Retrieve the property described by the given {@link PropertyMapping}.
	 * 
	 * @param propertyMapping
	 * @return
	 */
	Object getProperty(PropertyMapping propertyMapping);

}
