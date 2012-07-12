package org.om.core.impl.persistence.interceptor;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.interceptor.handler.ItemHandlerFactory;
import org.om.core.api.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link PersistenceInterceptor}, takes care of calling the
 * appropriate {@link ItemHandler}s and handles missing properties and default
 * values via {@link MissingBehaviorPersistenceDelegateDecorator}.
 * 
 * @author Jakob Külzer
 * 
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

	public Object get(MappedField mappedField) {
		if (mappedField == null)
			throw new NullPointerException("MappedField is null");

		final ItemHandler handler = handlerFactory.get(session, mappedField.getMapping());
		if (handler == null)
			throw new ObjectMapperException("No handler for mapped field " + mappedField.getName() + " found.");

		LOGGER.trace("Retrieving field {} with handler {}", mappedField.getName(), handler);

		return handler.retrieve(mappedField, adapter);
	}

}
