package com.omf.om.core.session.factory;

import com.omf.om.api.mapping.registry.MappingRegistry;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.PersistenceDelegateFactory;
import com.omf.om.api.session.Session;
import com.omf.om.api.session.factory.SessionFactory;
import com.omf.om.api.session.proxy.ProxyFactory;
import com.omf.om.core.session.ImmutableSessionImpl;

public class ImmutableSessionFactory implements SessionFactory {

	private final MappingRegistry mappingRegistry;
	private final ProxyFactory proxyFactory;
	private final PersistenceDelegateFactory persistenceDelegateFactory;

	public ImmutableSessionFactory(PersistenceDelegateFactory persistenceDelegateFactory, MappingRegistry mappingRegistry, ProxyFactory proxyFactory) {
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
	}

	public Session getSession(PersistenceContext persistenceContext) {
		return new ImmutableSessionImpl(persistenceContext, persistenceDelegateFactory, mappingRegistry, proxyFactory);
	}

}
