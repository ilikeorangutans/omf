package com.omf.om.core.persistence.delegate.cglib;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.core.mapping.EntityWithPlainProperties;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.persistence.delegate.TestingPersistenceDelegate;

public class CglibPersistenceInterceptorTest {

	@Test
	public void testInterceptWithValidField() throws Throwable {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPlainProperties.class);
		PersistenceDelegate persistenceDelegate = new TestingPersistenceDelegate(null, entityMapping, null).addProperty("fieldWithDefaultSettings", "booyah");
		CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(null, entityMapping, persistenceDelegate);

		EntityWithPlainProperties entity = new EntityWithPlainProperties();

		String result = (String) interceptor.intercept(entity, EntityWithPlainProperties.class.getMethod("getFieldWithDefaultSettings"), new Object[] {}, null);
		assertThat(result, notNullValue());
		assertThat(result, is("booyah"));
	}

	@Test
	public void testIsGetter() {

		CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(null, null, null);
		assertThat(interceptor.isGetter("getFoobar"), is(true));
		assertThat(interceptor.isGetter("getfoobar"), is(false));

		assertThat(interceptor.isGetter("doSomething"), is(false));
		assertThat(interceptor.isGetter("isAwesome"), is(true));
	}

	@Test
	public void testExtractFieldName() {

		CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(null, null, null);
		assertThat(interceptor.extractFieldName("getFoobar"), is("foobar"));
		assertThat(interceptor.extractFieldName("isFoobar"), is("foobar"));
		assertThat(interceptor.extractFieldName("getXYZ"), is("xYZ"));
	}
}
