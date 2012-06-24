package com.om.examples.example4.dao;

import org.om.dao.genericdao.GenericDAO;

import com.google.inject.ImplementedBy;
import com.om.examples.example4.pojo.MyPojo;

/**
 * @author tome
 */
@ImplementedBy(MyPojoDAOImpl.class)
public interface MyPojoDAO extends GenericDAO<MyPojo> {
}
