package org.om.core.impl.persistence.delegate.cglib;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.persistence.cglib.CglibPersistenceInterceptor;
import org.om.core.impl.persistence.delegate.TestingPersistenceAdapter;
import org.om.core.impl.persistence.interceptor.PersistenceInterceptorImpl;
import org.om.core.impl.persistence.interceptor.handler.ItemHandlerFactoryImpl;
import org.om.core.impl.test.EntityWithPrimitiveProperties;

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
