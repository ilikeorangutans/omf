package com.om.examples.example1;

import org.om.core.api.session.Session;
import org.om.dao.util.SessionUtil;

import com.om.examples.example1.dao.MyPojoDAO;
import com.om.examples.example1.pojo.MyPojo;

/**
 * @author tome
 */
public class Example1 {
   /**
    * void main
    */
   public static void main(String args[]) throws java.io.IOException, java.io.FileNotFoundException {
      /*
       * verify that we can get a session. This will also verify that XML autobeans are correctly configured and that the JCR can be connected to and logged in to
       */
      final Session session = SessionUtil.getSession();
      if (null != session) {
         new MyPojoDAO();
         /*
          * make an object
          */
         final MyPojo myPojo = new MyPojo();
         myPojo.setCount(12);
         myPojo.setId("tge");
         myPojo.setRate(13.3);
         /*
          * save the object
          */
         // dao.save(myPojo);
         /*
          * get the object back
          */
         // MyPojo retrievedPojo = dao.get(myPojo.getId());
         // System.out.println(retrievedPojo.getId());
      } else {
         System.out.println("Unable to get session, check your settings");
      }
   }
}
