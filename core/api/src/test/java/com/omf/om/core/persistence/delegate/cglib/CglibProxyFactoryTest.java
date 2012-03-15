package com.omf.om.core.persistence.delegate.cglib;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.PersistenceDelegateFactory;
import com.omf.om.core.mapping.EntityWithPlainProperties;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.persistence.cglib.CglibProxyFactory;
import com.omf.om.core.persistence.delegate.TestingDelegateFactory;
import com.omf.om.core.persistence.delegate.TestingPersistenceContext;

public class CglibProxyFactoryTest {

	@Test
	public void testThatCallsToProxyAreDelegated() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPlainProperties.class);
		final PersistenceDelegateFactory delegateFactory = new TestingDelegateFactory();
		final PersistenceContext persistenceContext = new TestingPersistenceContext().addProperty("fieldWithDefaultSettings", "BOOYAH!");
		final CglibProxyFactory proxyFactory = new CglibProxyFactory();

		EntityWithPlainProperties proxy = (EntityWithPlainProperties) proxyFactory.create(null, mapping,
				delegateFactory.create(null, null, mapping, persistenceContext));

		assertThat(proxy, notNullValue());
		String fieldWithDefaultSettings = proxy.getFieldWithDefaultSettings();

		assertThat(fieldWithDefaultSettings, notNullValue());
		assertThat(fieldWithDefaultSettings, is("BOOYAH!"));
		assertThat(proxy.getUnmappedField(), is("unmapped"));
	}

	@Test
	public void testThatCallsToNonMappedFields() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPlainProperties.class);
		final PersistenceDelegateFactory delegateFactory = new TestingDelegateFactory();
		final PersistenceContext persistenceContext = new TestingPersistenceContext().addProperty("fieldWithDefaultSettings", "BOOYAH!");
		final CglibProxyFactory proxyFactory = new CglibProxyFactory();

		EntityWithPlainProperties proxy = (EntityWithPlainProperties) proxyFactory.create(null, mapping,
				delegateFactory.create(null, null, mapping, persistenceContext));

		assertThat(proxy, notNullValue());
		assertThat(proxy.getUnmappedField(), is("unmapped"));
		assertThat(proxy.toString(), notNullValue());
	}
}
