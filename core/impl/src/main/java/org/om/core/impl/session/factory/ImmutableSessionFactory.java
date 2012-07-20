package org.om.core.impl.session.factory;

import javax.inject.Inject;

import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.session.ImmutableSessionImpl;

public class ImmutableSessionFactory implements SessionFactory {

	private final MappingRegistry mappingRegistry;
	private final PersistenceAdapterFactory persistenceDelegateFactory;
	private final ProxyFactory proxyFactory;

	@Inject
	public ImmutableSessionFactory(PersistenceAdapterFactory persistenceDelegateFactory, MappingRegistry mappingRegistry, ProxyFactory proxyFactory) {
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
	}

	public Session getSession(PersistenceContext persistenceContext) {
		return new ImmutableSessionImpl(persistenceContext, persistenceDelegateFactory, mappingRegistry, proxyFactory);
	}

}
