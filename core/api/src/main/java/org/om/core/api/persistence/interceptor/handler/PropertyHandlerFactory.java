package org.om.core.api.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.session.Session;

public interface PropertyHandlerFactory {

	/**
	 * Returns a handler for the given property.
	 * @param session 
	 * 
	 * @param mapping
	 * @param propertyMissing
	 * @return
	 */
	PropertyHandler get(Session session, PropertyMapping mapping);

}
