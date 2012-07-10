package org.om.core.impl.persistence.cglib;

import javax.inject.Inject;

import net.sf.cglib.proxy.Enhancer;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.Session;

/**
 * @author Jakob Külzer
 */
@Component
@Service(value = ProxyFactory.class)
public class CglibProxyFactory implements ProxyFactory {

	private final PersistenceInterceptorFactory interceptorFactory;

	@Inject
	public CglibProxyFactory(PersistenceInterceptorFactory interceptorFactory) {
		this.interceptorFactory = interceptorFactory;
	}

	public Object create(Session session, EntityMapping entityMapping, PersistenceAdapter persistenceDelegate) {
		final PersistenceInterceptor persistenceInterceptor = interceptorFactory.create(session, persistenceDelegate);
		final CglibPersistenceInterceptor methodInterceptor = new CglibPersistenceInterceptor(entityMapping, persistenceInterceptor);
		return Enhancer.create(entityMapping.getTypeClass(), methodInterceptor);
	}
}
