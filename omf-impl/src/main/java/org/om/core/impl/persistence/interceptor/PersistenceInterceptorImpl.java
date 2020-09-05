package org.om.core.impl.persistence.interceptor;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.*;
import org.om.core.api.persistence.interceptor.handler.*;
import org.om.core.api.session.*;
import org.slf4j.*;

/**
 * Implementation of {@link PersistenceInterceptor}, takes care of calling the appropriate
 * {@link ItemHandler}s and handles missing properties and default values via
 * {@link MissingBehaviorPersistenceDelegateDecorator}.
 *
 * @author Jakob Külzer
 */
public class PersistenceInterceptorImpl implements PersistenceInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceInterceptorImpl.class);
	private final PersistenceAdapter adapter;
	private final ItemHandlerFactory handlerFactory;
	private final Session session;

	public PersistenceInterceptorImpl(Session session, ItemHandlerFactory handlerFactory, PersistenceAdapter adapter) {
		this.session = session;
		this.handlerFactory = handlerFactory;
		this.adapter = adapter;
	}

	@Override
	public Object get(MappedField mappedField) {
		if (mappedField == null) {
			throw new IllegalArgumentException("MappedField is null");
		}
		final ItemHandler handler = handlerFactory.get(session, mappedField);
		if (handler == null) {
			throw new ObjectMapperException("No handler for mapped field " + mappedField.getName() + " found.");
		}
		LOGGER.trace("Retrieving field {} with handler {}", mappedField.getName(), handler);
		return handler.retrieve(mappedField, adapter);
	}
}
