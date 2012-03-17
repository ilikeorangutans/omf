package org.om.core.impl.persistence.jcr.impl;

import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.TransientRepositorySessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TestTransientRepositorySessionFactory {
	@Test
	public void test() {
		try {
			TransientRepositorySessionFactory sessionFactory = new TransientRepositorySessionFactory("target/testtransientrepo");
			Session session = sessionFactory.getSession();
			Assert.assertNotNull(session);
			session.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
