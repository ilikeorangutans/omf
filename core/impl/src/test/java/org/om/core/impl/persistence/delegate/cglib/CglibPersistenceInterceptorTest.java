package org.om.core.impl.persistence.delegate.cglib;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.persistence.cglib.CglibPersistenceInterceptor;
import org.om.core.impl.persistence.delegate.TestingPersistenceAdapter;
import org.om.core.impl.persistence.interceptor.PersistenceInterceptorImpl;
import org.om.core.impl.persistence.interceptor.handler.ItemHandlerFactoryImpl;

public class CglibPersistenceInterceptorTest {

	@Test
	public void testInterceptWithValidField() throws Throwable {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PersistenceAdapter persistenceDelegate = new TestingPersistenceAdapter(entityMapping, null).addProperty("fieldWithDefaultSettings", "booyah");
		CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(entityMapping, new PersistenceInterceptorImpl(null,
				new ItemHandlerFactoryImpl(), persistenceDelegate));

		EntityWithPrimitiveProperties entity = new EntityWithPrimitiveProperties();

		String result = (String) interceptor.intercept(entity, EntityWithPrimitiveProperties.class.getMethod("getFieldWithDefaultSettings"), new Object[] {},
				null);
		assertThat(result, notNullValue());
		assertThat(result, is("booyah"));
	}

}
