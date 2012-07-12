package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.ReferenceMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.request.ImmutablePersistenceRequest;
import org.om.core.api.persistence.request.Mode;
import org.om.core.api.persistence.result.PersistenceResult;
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
		final ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();

		// TODO: Need code here to handle locations
		final PersistenceResult result = adapter.getProperty(new ImmutablePersistenceRequest(mapping.getPath(), String.class, Mode.Relative));
		final Object id;
		if (result.hasResult()) {
			id = result.getResult();
		} else {
			id = MissingHandler.INSTANCE.retrieve(mappedField, adapter);
		}

		return session.get(mappedField.getType(), id);
	}
}
