package org.om.core.impl.persistence.jcr.test;

import org.om.core.api.mapping.extractor.*;
import org.om.core.api.mapping.registry.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.factory.*;
import org.om.core.api.persistence.proxy.*;
import org.om.core.api.session.factory.*;
import org.om.core.impl.mapping.extractor.*;
import org.om.core.impl.mapping.registry.*;
import org.om.core.impl.persistence.cglib.*;
import org.om.core.impl.persistence.interceptor.factory.*;
import org.om.core.impl.persistence.jcr.*;
import org.om.core.impl.session.factory.*;

import com.google.inject.*;

/**
 * Guice {@link Module} that binds all default implementations.
 *
 * @author Jakob Külzer
 */
public class ObjectMapperDefaultBindings extends AbstractModule {
	@Override
	protected void configure() {
		bind(MappingRegistry.class).to(OnDemandMappingRegistry.class);
		bind(EntityMappingExtractor.class).to(EntityMappingExtractorImpl.class);
		bind(SessionFactory.class).to(ImmutableSessionFactory.class);
		bind(PersistenceAdapterFactory.class).to(JcrPersistenceAdapterFactory.class);
		bind(ProxyFactory.class).to(CglibProxyFactory.class);
		bind(PersistenceInterceptorFactory.class).to(PersistenceInterceptorFactoryImpl.class);
	}
}
