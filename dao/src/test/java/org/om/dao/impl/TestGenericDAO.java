package org.om.dao.impl;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.path.Path;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.PropertiesConfiguredJCRSessionFactory;

/**
 * 
 * @author tome
 *         <p>
 *         This test needs JackRabbit 2.4.0 running in standalone mode on
 *         localhost
 *         </p>
 * 
 */
public class TestGenericDAO {

	@Before
	public void setUp() {
		try {
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			Assert.assertNotNull(session);
			/*
			 * get the root node
			 */
			final Node rootNode = session.getRootNode();
			Assert.assertNotNull(rootNode);
			/*
			 * add two nodes, one of which has properties
			 */
			final Node foo = rootNode.addNode("foo");
			final Node bar = foo.addNode("bar");
			bar.setProperty("foobar", "Horray!!");
			bar.setProperty("mycoolfield", "1000000");
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testRead() {
		try {
			/*
			 * DAO
			 */
			final TestEntityDAO testEntityDAO = new TestEntityDAO();
			/*
			 * use the DAO to get the Entity
			 */
			final TestEntity testEntity = testEntityDAO.get(new Path("foo/bar"));
			Assert.assertNotNull(testEntity);
			/*
			 * check
			 */
			Assert.assertNotNull(testEntity.getFoobar());
			Assert.assertTrue(testEntity.getFoobar().compareTo("Horray!!") == 0);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
