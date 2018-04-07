package org.om.core.impl.persistence.delegate.cglib;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.delegate.TestingDelegateFactory;
import org.om.core.impl.persistence.delegate.TestingPersistenceContext;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.test.EntityWithPrimitiveProperties;

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
