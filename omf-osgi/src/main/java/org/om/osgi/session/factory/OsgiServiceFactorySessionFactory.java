package org.om.osgi.session.factory;

import org.om.core.api.mapping.registry.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.proxy.*;
import org.om.core.api.session.factory.*;
import org.om.core.impl.session.factory.*;
import org.osgi.framework.*;
import org.slf4j.*;

/**
 * A {@link SessionFactory} that acts as a OSGI {@link ServiceFactory} to avoid class loading issues
 * in OSGI runtimes.
 *
 * @author Jakob Külzer
 */
public class OsgiServiceFactorySessionFactory implements ServiceFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(OsgiServiceFactorySessionFactory.class);
	private final MappingRegistry mappingRegistry;
	private final PersistenceAdapterFactory persistenceDelegateFactory;
	private final ProxyFactory proxyFactory;

	public OsgiServiceFactorySessionFactory(PersistenceAdapterFactory persistenceDelegateFactory, MappingRegistry mappingRegistry, ProxyFactory proxyFactory) {
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
	}

	@Override
	public Object getService(Bundle bundle, ServiceRegistration registration) {
		LOGGER.info("Creating new SessionFactory for bundle {}", bundle.toString());
		return new ImmutableSessionFactory(persistenceDelegateFactory, mappingRegistry, proxyFactory);
	}

	@Override
	public void ungetService(Bundle bundle, ServiceRegistration registration, Object service) {
		LOGGER.info("Destroying SessionFactory for bundle {}", bundle.toString());
	}
}
