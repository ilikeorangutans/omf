package org.om.core.impl.persistence.jcr.impl;

import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.ParameterizedSessionFactory;

/**
 * 
 * @author tome
 *         <p>
 *         This test needs JackRabbit 2.4.0 running in standalone mode on
 *         localhost
 *         </p>
 * 
 */
public class TestParameterizedSessionFactory {

	/**
	 * jackrabbit 2.4.0
	 */
	private static final String JACKRABBIT_2_4_0_RMI = "http://localhost:8080/rmi";

	@Test
	public void test() {
		try {
			ParameterizedSessionFactory sessionFactory = new ParameterizedSessionFactory(JACKRABBIT_2_4_0_RMI, null, "admin", "admin");
			Session session = sessionFactory.getSession();
			Assert.assertNotNull(session);
			session.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
