package org.om.core.impl.persistence.delegate.cglib;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.persistence.cglib.CglibPersistenceInterceptor;
import org.om.core.impl.persistence.delegate.TestingPersistenceDelegate;
import org.om.core.impl.persistence.interceptor.PersistenceInterceptorImpl;

public class CglibPersistenceInterceptorTest {

	@Test
	public void testInterceptWithValidField() throws Throwable {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PersistenceDelegate persistenceDelegate = new TestingPersistenceDelegate(entityMapping, null).addProperty("fieldWithDefaultSettings", "booyah");
		CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(entityMapping, new PersistenceInterceptorImpl(persistenceDelegate));

		EntityWithPrimitiveProperties entity = new EntityWithPrimitiveProperties();

		String result = (String) interceptor.intercept(entity, EntityWithPrimitiveProperties.class.getMethod("getFieldWithDefaultSettings"), new Object[] {},
				null);
		assertThat(result, notNullValue());
		assertThat(result, is("booyah"));
	}

	@Test
	public void testIsGetter() {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PersistenceDelegate persistenceDelegate = new TestingPersistenceDelegate(entityMapping, null).addProperty("fieldWithDefaultSettings", "booyah");

		CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(entityMapping, new PersistenceInterceptorImpl(persistenceDelegate));
		assertThat(interceptor.isGetter("getFoobar"), is(true));
		assertThat(interceptor.isGetter("getfoobar"), is(false));

		assertThat(interceptor.isGetter("doSomething"), is(false));
		assertThat(interceptor.isGetter("isAwesome"), is(true));
	}

	@Test
	public void testExtractFieldName() {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PersistenceDelegate persistenceDelegate = new TestingPersistenceDelegate(entityMapping, null).addProperty("fieldWithDefaultSettings", "booyah");

		CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(entityMapping, new PersistenceInterceptorImpl(persistenceDelegate));
		assertThat(interceptor.extractFieldName("getFoobar"), is("foobar"));
		assertThat(interceptor.extractFieldName("isFoobar"), is("foobar"));
		assertThat(interceptor.extractFieldName("getXYZ"), is("xYZ"));
	}
}
