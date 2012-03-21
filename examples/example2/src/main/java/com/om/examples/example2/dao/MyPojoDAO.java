package com.om.examples.example2.dao;

import org.om.dao.genericdao.GenericDAO;
import org.om.dao.genericdao.impl.GenericDAOImpl;

import com.om.examples.example2.pojo.MyPojo;

/**
 * 
 * @author tome
 * 
 */
public class MyPojoDAO extends GenericDAOImpl<MyPojo> implements GenericDAO<MyPojo> {
	/**
	 * ctor
	 */
	public MyPojoDAO() {
		super(MyPojo.class);
	}
}
