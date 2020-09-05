package org.om.osgi.activator;

import org.om.core.api.mapping.registry.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.proxy.*;
import org.om.core.api.session.factory.*;
import org.om.core.impl.mapping.extractor.*;
import org.om.core.impl.mapping.registry.*;
import org.om.core.impl.persistence.cglib.*;
import org.om.core.impl.persistence.interceptor.factory.*;
import org.om.core.impl.persistence.jcr.*;
import org.om.osgi.session.factory.*;
import org.osgi.framework.*;
import org.slf4j.*;

public class Activator implements BundleActivator {
	private static final Logger LOGGER = LoggerFactory.getLogger(Activator.class);
	private ServiceRegistration sessionFactoryRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		LOGGER.info("Object mapper bundle starting up... ");
		final PersistenceAdapterFactory persistenceDelegateFactory = new JcrPersistenceAdapterFactory();
		final MappingRegistry mappingRegistry = new SimpleCachingOnDemandMappingRegistry(new EntityMappingExtractorImpl());
		final ProxyFactory proxyFactory = new CglibProxyFactory(new PersistenceInterceptorFactoryImpl());
		final ServiceFactory serviceFactory = new OsgiServiceFactorySessionFactory(persistenceDelegateFactory, mappingRegistry, proxyFactory);
		sessionFactoryRegistration = context.registerService(SessionFactory.class.getName(), serviceFactory, null);
		LOGGER.info("Registered session factory.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		sessionFactoryRegistration.unregister();
	}
}
