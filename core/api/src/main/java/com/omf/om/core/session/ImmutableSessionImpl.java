package com.omf.om.core.session;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.registry.MappingRegistry;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.ProxyFactory;
import com.omf.om.api.session.Session;

public class ImmutableSessionImpl implements Session {

	private final MappingRegistry mappingRegistry;

	private final ProxyFactory proxyFactory;

	private final PersistenceContext persistenceContext;

	public ImmutableSessionImpl(PersistenceContext persistenceContext, MappingRegistry mappingRegistry, ProxyFactory proxyFactory) {
		this.persistenceContext = persistenceContext;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
	}

	public <T> T get(Class<T> clazz, String path) {
		if (clazz == null)
			throw new NullPointerException("Class is null.");

		final EntityMapping mapping = mappingRegistry.getMapping(clazz);
		return (T) proxyFactory.create(this, persistenceContext, mapping);
	}
}
