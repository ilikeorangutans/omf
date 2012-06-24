package com.om.examples.example4.service;

import com.google.inject.ImplementedBy;
import com.om.examples.example4.dao.MyPojoDAO;

/**
 * @author tome
 */
@ImplementedBy(MyServiceImpl.class)
public interface MyService {
   void doStuff();

   MyPojoDAO getMypojodao();
}
