package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.exception.PathNotFoundException;
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

	public Object retrieve(Mapping mapping, PersistenceAdapter adapter) {
		try {
			final Object object = adapter.getProperty((PropertyMapping) mapping);
			return session.get(mapping.getFieldType(), object);
		} catch (PathNotFoundException e) {
			switch (mapping.getMissingStrategy()) {
			case DefaultValue:
				// TODO: This doesn't make much sense in this context. I'm not
				// sure if I like the idea of a default value for a reference
				// field as it requires a second pass through the persistence
				// adapters.
				return null;

			case ThrowException:
				try {
					throw mapping.getMissingException().newInstance();
				} catch (InstantiationException e1) {
					throw new RuntimeException("Could not create exception " + mapping.getMissingException() + " to signal non-resolvable field "
							+ mapping.getFieldname(), e1);
				} catch (IllegalAccessException e1) {
					throw new RuntimeException("Could not create exception " + mapping.getMissingException() + " to signal non-resolvable field "
							+ mapping.getFieldname(), e1);
				}

			case ReturnNull:
			default:
				return null;
			}
		}
	}
}
