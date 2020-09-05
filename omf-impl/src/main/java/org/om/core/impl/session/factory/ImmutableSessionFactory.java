package org.om.core.impl.session.factory;

import javax.inject.*;

import org.om.core.api.mapping.registry.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.proxy.*;
import org.om.core.api.session.*;
import org.om.core.api.session.factory.*;
import org.om.core.impl.session.*;

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

	@Override
	public Session getSession(PersistenceContext persistenceContext) {
		return new ImmutableSessionImpl(persistenceContext, persistenceDelegateFactory, mappingRegistry, proxyFactory);
	}
}
