package org.om.core.api.persistence.interceptor.handler;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.session.Session;

public interface ItemHandlerFactory {

	/**
	 * Returns a handler for the given item.
	 * 
	 * @param session
	 * 
	 * @param mapping
	 * @param propertyMissing
	 * @return
	 */
	ItemHandler get(Session session, Mapping mapping);

}
