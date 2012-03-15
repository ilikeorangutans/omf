package com.omf.om.core.session;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.registry.MappingRegistry;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.PersistenceDelegateFactory;
import com.omf.om.api.session.Session;
import com.omf.om.api.session.proxy.ProxyFactory;

public class ImmutableSessionImpl implements Session {

	private final MappingRegistry mappingRegistry;

	private final ProxyFactory proxyFactory;

	private final PersistenceContext persistenceContext;

	private final PersistenceDelegateFactory persistenceDelegateFactory;

	public ImmutableSessionImpl(PersistenceContext persistenceContext, PersistenceDelegateFactory persistenceDelegateFactory, MappingRegistry mappingRegistry,
			ProxyFactory proxyFactory) {
		this.persistenceContext = persistenceContext;
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
	}

	public <T> T get(Class<T> clazz, Object id) {
		if (clazz == null)
			throw new NullPointerException("Class is null.");

		final EntityMapping entityMapping = mappingRegistry.getMapping(clazz);
		final PersistenceDelegate persistenceDelegate = persistenceDelegateFactory.create(this, id, entityMapping, persistenceContext);
		return (T) proxyFactory.create(this, entityMapping, persistenceDelegate);
	}
}
