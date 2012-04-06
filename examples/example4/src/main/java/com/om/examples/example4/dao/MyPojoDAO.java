package com.om.examples.example4.dao;

import org.om.dao.genericdao.GenericDAO;
import org.om.dao.genericdao.impl.GenericDAOImpl;

import com.khubla.simpleioc.annotation.RegistryBean;
import com.om.examples.example4.pojo.MyPojo;

/**
 * 
 * @author tome
 * 
 */
@RegistryBean(name = "mypojodao")
public class MyPojoDAO extends GenericDAOImpl<MyPojo> implements GenericDAO<MyPojo> {
	/**
	 * ctor
	 */
	public MyPojoDAO() {
		super(MyPojo.class);
	}
}
