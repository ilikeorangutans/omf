package com.omf.om.core.persistence.interceptor.factory;

import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.interceptor.PersistenceInterceptor;
import com.omf.om.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import com.omf.om.core.persistence.interceptor.PersistenceInterceptorImpl;

public class PersistenceInterceptorFactoryImpl implements PersistenceInterceptorFactory {

	public PersistenceInterceptor create(PersistenceDelegate delegate) {
		return new PersistenceInterceptorImpl(delegate);
	}

}
