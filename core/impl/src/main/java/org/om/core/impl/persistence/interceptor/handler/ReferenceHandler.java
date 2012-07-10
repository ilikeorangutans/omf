package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.exception.PathNotFoundException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
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
public class ReferenceHandler implements ItemHandler {

	private final Session session;

	public ReferenceHandler(Session session) {
		this.session = session;
	}

	@Override
	public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
		final CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();

		try {
			// TODO: Need code here to handle locations
			final Object object = adapter.getProperty((PropertyMapping) mapping);
			return session.get(mapping.getTargetType(), object);
		} catch (PathNotFoundException e) {
			switch (mappedField.getMissingStrategy()) {
			case DefaultValue:
				// TODO: This doesn't make much sense in this context. I'm not
				// sure if I like the idea of a default value for a reference
				// field as it requires a second pass through the persistence
				// adapters.
				return null;

			case ThrowException:
				try {
					throw mappedField.getMissingException().newInstance();
				} catch (InstantiationException e1) {
					throw new RuntimeException("Could not create exception " + mappedField.getMissingException() + " to signal non-resolvable field "
							+ mappedField.getName(), e1);
				} catch (IllegalAccessException e1) {
					throw new RuntimeException("Could not create exception " + mappedField.getMissingException() + " to signal non-resolvable field "
							+ mappedField.getName(), e1);
				}

			case ReturnNull:
			default:
				return null;
			}
		}
	}
}
