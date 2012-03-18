package org.om.core.impl.persistence.jcr.impl;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;

/**
 * session factory testing base class
 * 
 * @author tome
 * 
 */
public abstract class BaseTestSessionFactory {

	protected abstract SessionFactory getSessionFactory();

	private void recurse(Node node) throws RepositoryException {
		System.out.println("BaseTestSessionFactory.recurse() " + node.getPath());

		if (node.hasNodes()) {
			for (final NodeIterator ni = node.getNodes(); ni.hasNext();) {
				recurse(ni.nextNode());
			}
		}

	}

	@Test
	public void testlogin() throws Exception {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getSession();
		Assert.assertNotNull(session);
		session.logout();
	}

	@Test
	public void testJCR() throws Exception {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getSession();
		Assert.assertNotNull(session);
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
	}
}
