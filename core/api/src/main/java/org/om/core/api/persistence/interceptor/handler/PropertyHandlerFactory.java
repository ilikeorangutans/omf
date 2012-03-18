package org.om.core.api.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;

public interface PropertyHandlerFactory {

	/**
	 * Returns a handler for the given property.
	 * 
	 * @param mapping
	 * @param propertyMissing
	 * @return
	 */
	PropertyHandler get(PropertyMapping mapping);

}
