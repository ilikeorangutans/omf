package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.annotation.*;
import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.handler.*;
import org.om.core.api.persistence.request.*;
import org.om.core.api.persistence.result.*;
import org.om.core.api.session.*;
import org.slf4j.*;

/**
 * Handles properties whose values are references to other entities.
 *
 * @author Jakob Külzer
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
			if (result.hasResult() && (result.getValue() != null)) {
				id = result.getValue();
			} else {
				id = MissingHandler.INSTANCE.retrieve(mappedField, adapter);
			}
		}
		LOGGER.trace("Retrieving reference to {} from {}", mappedField.getType(), id);
		try {
			return session.get(mapping.getImplementationType(), id);
		} catch (final PathNotFoundException e) {
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
