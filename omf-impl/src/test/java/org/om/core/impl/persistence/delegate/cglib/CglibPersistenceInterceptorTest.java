package org.om.core.impl.persistence.delegate.cglib;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.impl.mapping.extractor.*;
import org.om.core.impl.persistence.cglib.*;
import org.om.core.impl.persistence.delegate.*;
import org.om.core.impl.persistence.interceptor.*;
import org.om.core.impl.persistence.interceptor.handler.*;
import org.om.core.impl.test.*;

public class CglibPersistenceInterceptorTest {
	@Test
	public void testInterceptWithValidField() throws Throwable {
		final EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		final PersistenceAdapter persistenceDelegate = new TestingPersistenceAdapter(entityMapping, null).addProperty("fieldWithDefaultSettings", "booyah");
		final CglibPersistenceInterceptor interceptor = new CglibPersistenceInterceptor(entityMapping, new PersistenceInterceptorImpl(null, new ItemHandlerFactoryImpl(), persistenceDelegate));
		final EntityWithPrimitiveProperties entity = new EntityWithPrimitiveProperties();
		final String result = (String) interceptor.intercept(entity, EntityWithPrimitiveProperties.class.getMethod("getFieldWithDefaultSettings"), new Object[] {}, null);
		assertThat(result, notNullValue());
		assertThat(result, is("booyah"));
	}
}
