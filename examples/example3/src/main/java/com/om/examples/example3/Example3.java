package com.om.examples.example3;

import org.om.core.api.path.Path;

import com.om.examples.example3.dao.MyPojoDAO;

/**
 * 
 * @author tome
 * 
 */
public class Example3 {
	/**
	 * void main
	 */
	public static void main(String args[]) throws java.io.IOException, java.io.FileNotFoundException {
		/*
		 * declare the DAO
		 */
		final MyPojoDAO dao = new MyPojoDAO();
		dao.get(new Path("/jcr/mystuff/myPojo"));
	}
}
