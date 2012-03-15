package com.omf.om.core.persistence.cglib;

import net.sf.cglib.proxy.Enhancer;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.session.Session;
import com.omf.om.api.session.proxy.ProxyFactory;

public class CglibProxyFactory implements ProxyFactory {

	public Object create(Session session, EntityMapping entityMapping, PersistenceDelegate persistenceDelegate) {
		final CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(persistenceDelegate, entityMapping);
		return Enhancer.create(entityMapping.getTypeClass(), interceptor);
	}
}
