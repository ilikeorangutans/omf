package org.om.core.impl.persistence.interceptor.factory;

import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.api.persistence.interceptor.handler.ItemHandlerFactory;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.interceptor.PersistenceInterceptorImpl;
import org.om.core.impl.persistence.interceptor.handler.ItemHandlerFactoryImpl;

/**
 * @author Jakob Külzers
 */
public class PersistenceInterceptorFactoryImpl implements PersistenceInterceptorFactory {

	private final ItemHandlerFactory propertyHandlerFactory;

	public PersistenceInterceptorFactoryImpl() {
		this(new ItemHandlerFactoryImpl());
	}

	public PersistenceInterceptorFactoryImpl(ItemHandlerFactory itemHandlerFactory) {
		this.propertyHandlerFactory = itemHandlerFactory;
	}

	public PersistenceInterceptor create(Session session, PersistenceAdapter delegate) {
		return new PersistenceInterceptorImpl(session, propertyHandlerFactory, delegate);
	}
}
