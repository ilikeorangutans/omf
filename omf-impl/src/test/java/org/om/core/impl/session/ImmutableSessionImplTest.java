package org.om.core.impl.session;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.delegate.TestingDelegateFactory;
import org.om.core.impl.persistence.delegate.TestingPersistenceContext;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.test.EntityWithPrimitiveProperties;
import org.om.core.impl.test.EntityWithReferenceProperties;

import junit.framework.Assert;

/**
 * @author Jakob Külzer
 * @author tom
 */
public class ImmutableSessionImplTest {
   @Test
   public void testGetWithPrimitiveProperties() {
      final MappingRegistry mappingRegistry = new OnDemandMappingRegistry(new EntityMappingExtractorImpl());
      final Session session = new ImmutableSessionImpl(null, new TestingDelegateFactory(), mappingRegistry, new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));
      final EntityWithPrimitiveProperties entity = session.get(EntityWithPrimitiveProperties.class, "");
      assertThat(entity, notNullValue());
      assertThat(entity instanceof EntityWithPrimitiveProperties, is(true));
   }

   @Test
   public void testGetWithReferenceProperties() {
      final MappingRegistry mappingRegistry = new OnDemandMappingRegistry(new EntityMappingExtractorImpl());
      final TestingPersistenceContext persistenceContext = new TestingPersistenceContext();
      persistenceContext.addProperty("entityWithPrimitiveProperties", "");
      final Session session = new ImmutableSessionImpl(persistenceContext, new TestingDelegateFactory(), mappingRegistry, new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));
      final EntityWithReferenceProperties entity = session.get(EntityWithReferenceProperties.class, "");
      assertThat(entity, notNullValue());
      assertThat(entity instanceof EntityWithReferenceProperties, is(true));
      final EntityWithPrimitiveProperties referencedEntity = entity.getEntityWithPrimitiveProperties();
      assertThat(referencedEntity, notNullValue());
   }

   @Test
   @Ignore
   public void testSetGetWithPrimitiveProperties() {
      /*
       * some setup
       */
      final MappingRegistry mappingRegistry = new OnDemandMappingRegistry(new EntityMappingExtractorImpl());
      final Session session = new ImmutableSessionImpl(null, new TestingDelegateFactory(), mappingRegistry, new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));
      Assert.assertNotNull(session);
      /*
       * an entity with an Id
       */
      final EntityWithPrimitiveProperties ewpp = new EntityWithPrimitiveProperties();
      ewpp.setId("tge");
      ewpp.setPrimitiveInt(15);
      /*
       * save it
       */
      session.save(ewpp);
      /*
       * get it back
       */
      final EntityWithPrimitiveProperties ewpp2 = session.get(EntityWithPrimitiveProperties.class, "tge");
      Assert.assertNotNull(ewpp2);
      System.out.println(ewpp2.getId());
      Assert.assertTrue(ewpp2.getId().compareTo("tge") == 0);
   }
}
