package org.om.dao.config;

import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.session.factory.SessionFactory;

/**
 * @author tome
 */
public interface ObjectManagerConfiguration {
   /**
    * get JCR persistence context
    */
   public PersistenceContext getPersistenceContext() throws Exception;

   /**
    * get a fully configured OM session factory
    */
   public SessionFactory getSessionFactory() throws Exception;
}
