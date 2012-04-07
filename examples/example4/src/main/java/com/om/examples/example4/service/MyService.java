package com.om.examples.example4.service;

import org.om.dao.genericdao.GenericDAO;

import com.om.examples.example4.pojo.MyPojo;

/**
 * 
 * @author tome
 * 
 */

public interface MyService {

	void doStuff();

	GenericDAO<MyPojo> getMypojodao();

}
