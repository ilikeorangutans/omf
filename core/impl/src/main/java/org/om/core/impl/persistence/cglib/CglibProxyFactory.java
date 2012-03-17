package org.om.core.impl.persistence.cglib;

import net.sf.cglib.proxy.Enhancer;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.api.session.Session;
import org.om.core.api.session.proxy.ProxyFactory;

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
