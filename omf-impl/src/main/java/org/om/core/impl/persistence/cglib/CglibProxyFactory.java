package org.om.core.impl.persistence.cglib;

import javax.inject.*;

import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.*;
import org.om.core.api.persistence.interceptor.factory.*;
import org.om.core.api.persistence.proxy.*;
import org.om.core.api.session.*;

import net.sf.cglib.proxy.*;

/**
 * @author Jakob Külzer
 */
public class CglibProxyFactory implements ProxyFactory {
	private final PersistenceInterceptorFactory interceptorFactory;

	@Inject
	public CglibProxyFactory(PersistenceInterceptorFactory interceptorFactory) {
		this.interceptorFactory = interceptorFactory;
	}

	@Override
	public Object create(Session session, EntityMapping entityMapping, PersistenceAdapter persistenceDelegate) {
		final PersistenceInterceptor persistenceInterceptor = interceptorFactory.create(session, persistenceDelegate);
		final CglibPersistenceInterceptor methodInterceptor = new CglibPersistenceInterceptor(entityMapping, persistenceInterceptor);
		try {
			return Enhancer.create(entityMapping.getTypeClass(), methodInterceptor);
		} catch (final Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
