package org.om.core.impl.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.session.factory.ImmutableSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public abstract class BaseJCRSessionTest {
	protected abstract JCRSessionFactory getSessionFactory();

	@Test
	public void test1() {
		try {
			final Session session = getSessionFactory().getSession();
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
			/*
			 * test some OM stuff
			 */
			final SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
					new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

			final org.om.core.api.session.Session s = sessionFactory.getSession(new JcrPersistenceContext(getSessionFactory()));
			Assert.assertNotNull(s);
			/*
			 * get the entity
			 */
			final TestEntity testEntity = s.get(TestEntity.class, "foo/bar");
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
