package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.handler.PropertyHandler;
import org.om.core.api.session.Session;

/**
 * Handles properties whose values are references to other entities.
 * 
 * @author Jakob Külzer
 * 
 */
public class ReferencePropertyHandler implements PropertyHandler {

	private final Session session;

	public ReferencePropertyHandler(Session session) {
		this.session = session;
	}

	public Object retrieve(PropertyMapping propertyMapping, Object input) {
		if (input == null)
			throw new NullPointerException("Cannot resolve reference, value given as key is null");

		return session.get(propertyMapping.getPropertyType(), input);
	}

}
