package org.om.core.impl.persistence.interceptor;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.interceptor.handler.ItemHandlerFactory;
import org.om.core.api.session.Session;

/**
 * Implementation of {@link PersistenceInterceptor}, takes care of calling the
 * appropriate {@link ItemHandler}s and handles missing properties and default
 * values via {@link MissingBehaviorPersistenceDelegateDecorator}.
 * 
 * @author Jakob Külzer
 * 
 */
public class PersistenceInterceptorImpl implements PersistenceInterceptor {

	private final PersistenceDelegate delegate;

	private final ItemHandlerFactory handlerFactory;

	private final Session session;

	public PersistenceInterceptorImpl(Session session, ItemHandlerFactory handlerFactory, PersistenceDelegate delegate) {
		this.session = session;
		this.handlerFactory = handlerFactory;
		this.delegate = new MissingBehaviorPersistenceDelegateDecorator(delegate);
	}

	public Object getProperty(Mapping mapping) {
		if (mapping == null)
			throw new NullPointerException("Mapping is null");

		final ItemHandler handler = handlerFactory.get(session, mapping);
		assert handler != null : "No handler for property mapping " + mapping + " found.";

		return handler.retrieve(mapping, delegate);
	}

}
