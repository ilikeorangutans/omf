package org.om.examples.example4;

import junit.framework.Assert;

import org.junit.Test;
import org.om.dao.annotation.transactional.filter.JCRTransactionalModule;
import org.om.dao.genericdao.GenericDAO;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.om.examples.example4.Example4;
import com.om.examples.example4.pojo.MyPojo;
import com.om.examples.example4.service.MyService;

/**
 * @author tome
 */
public class TestExample {
   @Test
   public void test() {
      try {
         Example4.main(null);
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   /**
    * test that we can get the service and that it has the DAO
    */
   @Test
   public void test2() {
      try {
         Injector injector = Guice.createInjector(new JCRTransactionalModule());
         MyService svc = injector.getInstance(MyService.class);
         Assert.assertNotNull(svc);
         GenericDAO<MyPojo> dao = svc.getMypojodao();
         Assert.assertNotNull(dao);
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
