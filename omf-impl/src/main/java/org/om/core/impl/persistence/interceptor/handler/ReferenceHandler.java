package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.annotation.LookupMode;
import org.om.core.api.exception.MissingException;
import org.om.core.api.exception.PathNotFoundException;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.ReferenceMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.request.ImmutablePersistenceRequest;
import org.om.core.api.persistence.request.Mode;
import org.om.core.api.persistence.result.PersistenceResult;
import org.om.core.api.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles properties whose values are references to other entities.
 * 
 * @author Jakob Külzer
 * 
 */
public class ReferenceHandler implements ItemHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceHandler.class);

	private final Session session;

	public ReferenceHandler(Session session) {
		this.session = session;
	}

	@Override
	public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
		final ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();

		// TODO: Need better code here to handle locations
		Object id = adapter.resolve(mapping.getPath());

		LOGGER.debug("Path (resolved) from mapping is {} ({})", mapping.getPath(), id);
		if (mapping.getLookupMode() == LookupMode.Reference) {
			LOGGER.trace("Field {} is a reference, retrieving value of local field to resolve reference...", mappedField.getName());
			// We're using the value of the local property as the path to lookup
			// the object.

			final PersistenceResult result = adapter.getProperty(new ImmutablePersistenceRequest(mapping.getPath(), String.class, Mode.Relative));
			if (result.hasResult()) {
				id = result.getValue();
			} else {
				id = MissingHandler.INSTANCE.retrieve(mappedField, adapter);
			}
		}

		LOGGER.trace("Retrieving reference to {} from {}", mappedField.getType(), id);

		try {

			return session.get(mappedField.getType(), id);
		} catch (PathNotFoundException e) {
			switch (mappedField.getMissingStrategy()) {
			case ReturnNull:
				return null;
			case DefaultValue:
				// TODO: handle default value
			case ThrowException:
			default:
				// TODO: throw appropriate exception type
				throw new MissingException("Cannot find " + id + " while retrieving " + mappedField, e);
			}
		}
	}

	@Override
	public String toString() {
		return "ReferenceHandler [session=" + session + "]";
	}
}
