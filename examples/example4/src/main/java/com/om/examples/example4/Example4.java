package com.om.examples.example4;

import org.om.dao.annotation.transactional.filter.JCRTransactionalModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.om.examples.example4.service.MyService;

/**
 * @author tome
 */
public class Example4 {
   /**
    * service
    */
   private static Injector injector = Guice.createInjector(new JCRTransactionalModule());

   /**
    * void main
    */
   public static void main(String args[]) throws java.io.IOException, java.io.FileNotFoundException {
      MyService svc = injector.getInstance(MyService.class);
      svc.doStuff();
   }
}
