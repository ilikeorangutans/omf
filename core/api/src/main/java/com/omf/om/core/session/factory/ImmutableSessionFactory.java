package com.omf.om.core.session.factory;

import com.omf.om.api.mapping.registry.MappingRegistry;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.ProxyFactory;
import com.omf.om.api.session.Session;
import com.omf.om.api.session.factory.SessionFactory;
import com.omf.om.core.session.ImmutableSessionImpl;

public class ImmutableSessionFactory implements SessionFactory {

	private final MappingRegistry mappingRegistry;
	private final ProxyFactory proxyFactory;

	public ImmutableSessionFactory(MappingRegistry mappingRegistry, ProxyFactory proxyFactory) {
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
	}

	public Session getSession(PersistenceContext persistenceContext) {
		return new ImmutableSessionImpl(persistenceContext, mappingRegistry, proxyFactory);
	}

}
