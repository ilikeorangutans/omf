package org.om.core.impl.persistence.interceptor.factory;

import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.impl.persistence.interceptor.PersistenceInterceptorImpl;

public class PersistenceInterceptorFactoryImpl implements PersistenceInterceptorFactory {

	public PersistenceInterceptor create(PersistenceDelegate delegate) {
		return new PersistenceInterceptorImpl(delegate);
	}

}
