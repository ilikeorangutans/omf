package org.om.dao.util;

import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.dao.config.ObjectManagerConfiguration;
import org.om.dao.config.impl.XMLObjectManagerConfiguration;

/**
 * @author tome
 */
public class SessionUtil {
   /**
    * the OM config
    */
   private static ObjectManagerConfiguration objectManagerConfiguration = null;

   /**
    * get a JCR context
    */
   public static PersistenceContext getPersistenceContext() throws JCRException {
      try {
         /*
          * init if needed
          */
         init();
         /*
          * session, via the JCR context
          */
         return objectManagerConfiguration.getPersistenceContext();
      } catch (final Exception e) {
         throw new JCRException("Exception in getSession", e);
      }
   }

   /**
    * get OM session
    */
   public static Session getSession() throws JCRException {
      try {
         /*
          * init if needed
          */
         init();
         /*
          * the sessionfactory
          */
         final SessionFactory sessionFactory = objectManagerConfiguration.getSessionFactory();
         /*
          * session, via the JCR context
          */
         return sessionFactory.getSession(objectManagerConfiguration.getPersistenceContext());
      } catch (final Exception e) {
         throw new JCRException("Exception in getSession", e);
      }
   }

   /**
    * init
    */
   private static void init() throws JCRException {
      try {
         if (null == objectManagerConfiguration) {
            objectManagerConfiguration = XMLObjectManagerConfiguration.getObjectManagerConfiguration();
         }
      } catch (final Exception e) {
         throw new JCRException("Exception in init", e);
      }
   }
}
