package org.om.core.impl.persistence.jcr.impl;

import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.ConfiguredSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TestConfiguredSessionFactory {

	@Test
	public void test() {
		try {
			ConfiguredSessionFactory sessionFactory = new ConfiguredSessionFactory("/configuredsessionfactory.properties");
			Session session = sessionFactory.getSession();
			Assert.assertNotNull(session);
			session.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
