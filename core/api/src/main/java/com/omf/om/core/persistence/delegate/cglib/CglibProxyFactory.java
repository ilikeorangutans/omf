package com.omf.om.core.persistence.delegate.cglib;

import net.sf.cglib.proxy.Enhancer;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.PersistenceDelegateFactory;
import com.omf.om.api.persistence.ProxyFactory;
import com.omf.om.api.session.Session;

public class CglibProxyFactory implements ProxyFactory {

	private final PersistenceDelegateFactory delegateFactory;

	public CglibProxyFactory(PersistenceDelegateFactory delegateFactory) {
		this.delegateFactory = delegateFactory;
	}

	public Object create(Session session, PersistenceContext persistenceContext, EntityMapping entityMapping) {
		final PersistenceDelegate delegate = delegateFactory.create(session, entityMapping, persistenceContext);
		final CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(session, entityMapping, delegate);

		return Enhancer.create(entityMapping.getTypeClass(), interceptor);
	}
}
