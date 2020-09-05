package org.om.core.impl.persistence.delegate.cglib;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.impl.mapping.extractor.*;
import org.om.core.impl.persistence.cglib.*;
import org.om.core.impl.persistence.delegate.*;
import org.om.core.impl.persistence.interceptor.factory.*;
import org.om.core.impl.test.*;

/**
 * @author Jakob Külzer
 */
public class CglibProxyFactoryTest {
	@Test
	public void testThatCallsToNonMappedFields() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		final PersistenceAdapterFactory delegateFactory = new TestingDelegateFactory();
		final PersistenceContext persistenceContext = new TestingPersistenceContext().addProperty("fieldWithDefaultSettings", "BOOYAH!");
		final CglibProxyFactory proxyFactory = new CglibProxyFactory(new PersistenceInterceptorFactoryImpl());
		final EntityWithPrimitiveProperties proxy = (EntityWithPrimitiveProperties) proxyFactory.create(null, mapping, delegateFactory.create(null, mapping, persistenceContext));
		assertThat(proxy, notNullValue());
		assertThat(proxy.getUnmappedField(), is("unmapped"));
		assertThat(proxy.toString(), notNullValue());
	}

	@Test
	public void testThatCallsToProxyAreDelegated() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		final PersistenceAdapterFactory delegateFactory = new TestingDelegateFactory();
		final PersistenceContext persistenceContext = new TestingPersistenceContext().addProperty("fieldWithDefaultSettings", "BOOYAH!");
		final CglibProxyFactory proxyFactory = new CglibProxyFactory(new PersistenceInterceptorFactoryImpl());
		final EntityWithPrimitiveProperties proxy = (EntityWithPrimitiveProperties) proxyFactory.create(null, mapping, delegateFactory.create(null, mapping, persistenceContext));
		assertThat(proxy, notNullValue());
		final String fieldWithDefaultSettings = proxy.getFieldWithDefaultSettings();
		assertThat(fieldWithDefaultSettings, notNullValue());
		assertThat(fieldWithDefaultSettings, is("BOOYAH!"));
		assertThat(proxy.getUnmappedField(), is("unmapped"));
	}
}
