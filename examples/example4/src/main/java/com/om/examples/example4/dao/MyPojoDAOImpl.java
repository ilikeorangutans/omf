package com.om.examples.example4.dao;

import org.om.dao.genericdao.impl.GenericDAOImpl;

import com.om.examples.example4.pojo.MyPojo;

/**
 * @author tome
 */
public class MyPojoDAOImpl extends GenericDAOImpl<MyPojo> implements MyPojoDAO {
   /**
    * ctor
    */
   public MyPojoDAOImpl() {
      super(MyPojo.class);
   }
}