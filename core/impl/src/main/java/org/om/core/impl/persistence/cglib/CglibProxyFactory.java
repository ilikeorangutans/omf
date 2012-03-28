package org.om.core.impl.persistence.cglib;

import net.sf.cglib.proxy.Enhancer;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.Session;

/**
 * @author Jakob Külzer
 */
public class CglibProxyFactory implements ProxyFactory {

	private final PersistenceInterceptorFactory interceptorFactory;

	public CglibProxyFactory(PersistenceInterceptorFactory interceptorFactory) {
		this.interceptorFactory = interceptorFactory;
	}

	public Object create(Session session, EntityMapping entityMapping, PersistenceDelegate persistenceDelegate) {
		PersistenceInterceptor persistenceInterceptor = interceptorFactory.create(session, persistenceDelegate);
		final CglibPersistenceInterceptor methodInterceptor = new CglibPersistenceInterceptor(entityMapping, persistenceInterceptor);
		return Enhancer.create(entityMapping.getTypeClass(), methodInterceptor);
	}
}
