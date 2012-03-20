package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

/**
 * session factory testing base class
 * 
 * @author tome
 * 
 */
public abstract class BaseTestSessionFactory {

	protected abstract JCRSessionFactory getSessionFactory();

	private void recurse(Node node) throws RepositoryException {
		System.out.println("BaseTestSessionFactory.recurse() " + node.getPath());

		if (node.hasNodes()) {
			for (final NodeIterator ni = node.getNodes(); ni.hasNext();) {
				recurse(ni.nextNode());
			}
		}

	}

	@Test
	public void testJCR() throws Exception {
		final JCRSessionFactory sessionFactory = getSessionFactory();
		final Session session = sessionFactory.getSession();
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

		recurse(rootNode);
		session.logout();
	}

	@Test
	public void testlogin() throws Exception {
		final JCRSessionFactory sessionFactory = getSessionFactory();
		final Session session = sessionFactory.getSession();
		Assert.assertNotNull(session);
		session.logout();
	}
}
