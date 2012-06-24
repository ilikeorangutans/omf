package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.session.Session;

/**
 * Handles properties whose values are references to other entities.
 * 
 * @author Jakob Külzer
 * 
 */
public class ReferencePropertyHandler implements ItemHandler {

	private final Session session;

	public ReferencePropertyHandler(Session session) {
		this.session = session;
	}

	public Object retrieve(Mapping mapping, PersistenceAdapter delegate) {
		return session.get(mapping.getFieldType(), delegate.getProperty((PropertyMapping) mapping));
	}

}
