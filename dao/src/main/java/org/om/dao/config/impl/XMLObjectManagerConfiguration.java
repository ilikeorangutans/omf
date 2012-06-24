package org.om.dao.config.impl;

import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.core.impl.persistence.jcr.JcrPersistenceDelegateFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.PropertiesConfiguredJCRSessionFactory;
import org.om.core.impl.session.factory.DefaultSessionFactory;
import org.om.dao.config.ObjectManagerConfiguration;

/**
 * @author tome
 */
public class XMLObjectManagerConfiguration implements ObjectManagerConfiguration {
   /**
    * singleton getter
    */
   public static ObjectManagerConfiguration getObjectManagerConfiguration() throws JCRException {
      if (null == instance) {
         instance = new XMLObjectManagerConfiguration();
      }
      return instance;
   }

   /**
    * OM session factory
    */
   private SessionFactory sessionFactory;
   /**
    * persistence context
    */
   private PersistenceContext persistenceContext;
   /**
    * singleton
    */
   private static XMLObjectManagerConfiguration instance = null;

   /**
    * private ctor
    */
   private XMLObjectManagerConfiguration() throws JCRException {
      try {
         configure();
      } catch (final Exception e) {
         throw new JCRException("Exception in XMLObjectManagerConfiguration ctor", e);
      }
   }

   public PersistenceContext getPersistenceContext() throws Exception {
      return persistenceContext;
   }

   public SessionFactory getSessionFactory() throws Exception {
      return sessionFactory;
   }

   /**
    * configure the OM Configuration
    */
   private void configure() throws JCRException {
      try {
         JCRSessionFactory jcrSessionFactory = new PropertiesConfiguredJCRSessionFactory();
         persistenceContext = new JcrPersistenceContext(jcrSessionFactory);
         JcrPersistenceDelegateFactory jcrPersistenceDelegateFactory = new JcrPersistenceDelegateFactory();
         sessionFactory = new DefaultSessionFactory(jcrPersistenceDelegateFactory);
      } catch (final Exception e) {
         throw new JCRException("Exception in parseConfiguration ctor", e);
      }
   }
}
