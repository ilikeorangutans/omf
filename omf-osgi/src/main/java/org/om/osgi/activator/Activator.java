package org.om.osgi.activator;

import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.JcrPersistenceAdapterFactory;
import org.om.core.impl.session.factory.ImmutableSessionFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(Activator.class);

	private ServiceRegistration sessionFactoryRegistration;

	public void start(BundleContext context) throws Exception {
		LOGGER.info("Object mapper bundle starting up... ");
		PersistenceAdapterFactory persistenceDelegateFactory = new JcrPersistenceAdapterFactory();
		MappingRegistry mappingRegistry = new OnDemandMappingRegistry(new EntityMappingExtractorImpl());
		ProxyFactory proxyFactory = new CglibProxyFactory(new PersistenceInterceptorFactoryImpl());
		SessionFactory sessionFactory = new ImmutableSessionFactory(persistenceDelegateFactory, mappingRegistry, proxyFactory);

		sessionFactoryRegistration = context.registerService(SessionFactory.class.getName(), sessionFactory, null);
		LOGGER.info("Registered session factory.");
	}

	public void stop(BundleContext context) throws Exception {
		sessionFactoryRegistration.unregister();
	}

}
