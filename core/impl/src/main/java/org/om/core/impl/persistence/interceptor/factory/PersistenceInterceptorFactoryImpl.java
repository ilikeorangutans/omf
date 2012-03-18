package org.om.core.impl.persistence.interceptor.factory;

import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.impl.persistence.interceptor.PersistenceInterceptorImpl;
import org.om.core.impl.persistence.interceptor.handler.PropertyHandlerFactoryImpl;

public class PersistenceInterceptorFactoryImpl implements PersistenceInterceptorFactory {

	public PersistenceInterceptor create(PersistenceDelegate delegate) {
		return new PersistenceInterceptorImpl(new PropertyHandlerFactoryImpl(), delegate);
	}

}
