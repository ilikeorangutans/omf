package org.om.core.impl.persistence.interceptor.factory;

import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.*;
import org.om.core.api.persistence.interceptor.factory.*;
import org.om.core.api.persistence.interceptor.handler.*;
import org.om.core.api.session.*;
import org.om.core.impl.persistence.interceptor.*;
import org.om.core.impl.persistence.interceptor.handler.*;

/**
 * @author Jakob Külzers
 */
public class PersistenceInterceptorFactoryImpl implements PersistenceInterceptorFactory {
	private final ItemHandlerFactory propertyHandlerFactory;

	public PersistenceInterceptorFactoryImpl() {
		this(new ItemHandlerFactoryImpl());
	}

	public PersistenceInterceptorFactoryImpl(ItemHandlerFactory itemHandlerFactory) {
		propertyHandlerFactory = itemHandlerFactory;
	}

	@Override
	public PersistenceInterceptor create(Session session, PersistenceAdapter delegate) {
		return new PersistenceInterceptorImpl(session, propertyHandlerFactory, delegate);
	}
}
