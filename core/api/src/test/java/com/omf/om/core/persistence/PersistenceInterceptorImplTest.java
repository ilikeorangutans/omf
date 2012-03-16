package com.omf.om.core.persistence;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.core.mapping.EntityWithPrimitiveProperties;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.persistence.delegate.TestingPersistenceContext;
import com.omf.om.core.persistence.delegate.TestingPersistenceDelegate;

public class PersistenceInterceptorImplTest {

	@Test
	public void test() {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PersistenceInterceptorImpl interceptor = new PersistenceInterceptorImpl(new TestingPersistenceDelegate(entityMapping, new TestingPersistenceContext()));

		interceptor.getProperty(null);
		fail("Not yet implemented");
	}
}
