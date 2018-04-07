package org.om.osgi.activator;

import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.SimpleCachingOnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.JcrPersistenceAdapterFactory;
import org.om.osgi.session.factory.OsgiServiceFactorySessionFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
