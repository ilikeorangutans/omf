package com.omf.om.core.persistence.cglib;

import net.sf.cglib.proxy.Enhancer;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.interceptor.PersistenceInterceptor;
import com.omf.om.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import com.omf.om.api.session.Session;
import com.omf.om.api.session.proxy.ProxyFactory;

public class CglibProxyFactory implements ProxyFactory {

	private final PersistenceInterceptorFactory interceptorFactory;

	public CglibProxyFactory(PersistenceInterceptorFactory interceptorFactory) {
		this.interceptorFactory = interceptorFactory;
	}

	public Object create(Session session, EntityMapping entityMapping, PersistenceDelegate persistenceDelegate) {
		PersistenceInterceptor persistenceInterceptor = interceptorFactory.create(persistenceDelegate);
		final CglibPersistenceInterceptor methodInterceptor = new CglibPersistenceInterceptor(entityMapping, persistenceInterceptor);
		return Enhancer.create(entityMapping.getTypeClass(), methodInterceptor);
	}
}
