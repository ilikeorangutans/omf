package com.omf.om.core.session;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.omf.om.api.mapping.registry.MappingRegistry;
import com.omf.om.api.session.Session;
import com.omf.om.core.mapping.EntityWithPlainProperties;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.mapping.registry.OnDemandMappingRegistry;
import com.omf.om.core.persistence.delegate.TestingDelegateFactory;
import com.omf.om.core.persistence.delegate.cglib.CglibProxyFactory;

public class ImmutableSessionImplTest {

	@Test
	public void testGet() {
		MappingRegistry mappingRegistry = new OnDemandMappingRegistry(new EntityMappingExtractorImpl());
		Session session = new ImmutableSessionImpl(null, mappingRegistry, new CglibProxyFactory(new TestingDelegateFactory()));
		EntityWithPlainProperties entity = session.get(EntityWithPlainProperties.class, "");

		assertThat(entity, notNullValue());
		assertThat(entity instanceof EntityWithPlainProperties, is(true));
	}

}
