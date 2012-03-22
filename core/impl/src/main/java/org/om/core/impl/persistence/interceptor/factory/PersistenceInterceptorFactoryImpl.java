package org.om.core.impl.persistence.interceptor.factory;

import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.api.persistence.interceptor.handler.PropertyHandlerFactory;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.interceptor.PersistenceInterceptorImpl;
import org.om.core.impl.persistence.interceptor.handler.PropertyHandlerFactoryImpl;

/**
 * @author Jakob Külzers
 */
public class PersistenceInterceptorFactoryImpl implements PersistenceInterceptorFactory {

	private final PropertyHandlerFactory propertyHandlerFactory;

	public PersistenceInterceptorFactoryImpl() {
		this(new PropertyHandlerFactoryImpl());
	}

	public PersistenceInterceptorFactoryImpl(PropertyHandlerFactory propertyHandlerFactory) {
		this.propertyHandlerFactory = propertyHandlerFactory;
	}

	public PersistenceInterceptor create(Session session, PersistenceDelegate delegate) {
		return new PersistenceInterceptorImpl(session, propertyHandlerFactory, delegate);
	}
}
