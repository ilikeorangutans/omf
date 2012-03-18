package org.om.core.impl.session;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.EntityWithReferenceProperties;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.delegate.TestingDelegateFactory;
import org.om.core.impl.persistence.delegate.TestingPersistenceContext;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;

public class ImmutableSessionImplTest {

	@Test
	public void testGetWithPrimitiveProperties() {
		MappingRegistry mappingRegistry = new OnDemandMappingRegistry(new EntityMappingExtractorImpl());
		Session session = new ImmutableSessionImpl(null, new TestingDelegateFactory(), mappingRegistry, new CglibProxyFactory(
				new PersistenceInterceptorFactoryImpl()));
		EntityWithPrimitiveProperties entity = session.get(EntityWithPrimitiveProperties.class, "");

		assertThat(entity, notNullValue());
		assertThat(entity instanceof EntityWithPrimitiveProperties, is(true));
	}

	@Test
	public void testGetWithReferenceProperties() {
		MappingRegistry mappingRegistry = new OnDemandMappingRegistry(new EntityMappingExtractorImpl());
		TestingPersistenceContext persistenceContext = new TestingPersistenceContext();
		persistenceContext.addProperty("entityWithPrimitiveProperties", "");
		Session session = new ImmutableSessionImpl(persistenceContext, new TestingDelegateFactory(), mappingRegistry, new CglibProxyFactory(
				new PersistenceInterceptorFactoryImpl()));
		EntityWithReferenceProperties entity = session.get(EntityWithReferenceProperties.class, "");

		assertThat(entity, notNullValue());
		assertThat(entity instanceof EntityWithReferenceProperties, is(true));

		EntityWithPrimitiveProperties referencedEntity = entity.getEntityWithPrimitiveProperties();
		assertThat(referencedEntity, notNullValue());
	}

}