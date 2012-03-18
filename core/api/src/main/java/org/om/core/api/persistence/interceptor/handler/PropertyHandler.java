package org.om.core.api.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;

/**
 * Access to properties.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PropertyHandler {

	/**
	 * Retrieves the given property from the delegate.
	 * 
	 * @param propertyMapping
	 * @param delegate
	 * @return
	 */
	Object retrieve(PropertyMapping propertyMapping, Object input);

}
