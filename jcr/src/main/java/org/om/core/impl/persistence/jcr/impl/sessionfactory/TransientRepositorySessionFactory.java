package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import java.io.File;

import javax.jcr.Repository;
import javax.jcr.Session;

import org.apache.jackrabbit.core.TransientRepository;
import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * @author tome
 */
public class TransientRepositorySessionFactory implements SessionFactory {
   /**
    * path
    */
   private final String path;

   /**
    * ctor
    */
   public TransientRepositorySessionFactory(String path) {
      this.path = path;
   }

   public Session getSession() throws JCRException {
      try {
         File file = new File(".");
         Repository repository = new TransientRepository(new File(file, path));
         return repository.login();
      } catch (Exception e) {
         throw new JCRException("Exception in getSession", e);
      }
   }
}
