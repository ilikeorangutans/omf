package org.om.core.impl;

import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.persistence.PersistenceDelegateFactory;
import org.om.core.api.persistence.interceptor.factory.PersistenceInterceptorFactory;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.JcrPersistenceDelegateFactory;
import org.om.core.impl.session.factory.ImmutableSessionFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * Guice {@link Module} that binds all default implementations.
 * 
 * @author Jakob Külzer
 * 
 */
public class ObjectMapperDefaultBindings extends AbstractModule {

	@Override
	protected void configure() {
		bind(MappingRegistry.class).to(OnDemandMappingRegistry.class);
		bind(EntityMappingExtractor.class).to(EntityMappingExtractorImpl.class);
		bind(SessionFactory.class).to(ImmutableSessionFactory.class);
		bind(PersistenceDelegateFactory.class).to(JcrPersistenceDelegateFactory.class);
		bind(ProxyFactory.class).to(CglibProxyFactory.class);
		bind(PersistenceInterceptorFactory.class).to(PersistenceInterceptorFactoryImpl.class);
	}

}
