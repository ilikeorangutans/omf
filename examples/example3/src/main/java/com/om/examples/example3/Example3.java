package com.om.examples.example3;

import org.om.core.api.session.Session;
import org.om.dao.util.SessionUtil;

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
		 * verify that we can get a session. This will also verify that XML
		 * autobeans are correctly configured and that the JCR can be connected
		 * to and logged in to
		 */
		Session session = SessionUtil.getSession();
		/*
		 * declare the DAO
		 */
		final MyPojoDAO dao = new MyPojoDAO();
		// dao.get(new Path("/jcr/mystuff/myPojo"));
	}
}
