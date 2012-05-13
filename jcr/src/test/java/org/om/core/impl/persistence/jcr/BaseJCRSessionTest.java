package org.om.core.impl.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.entity.TestEntity;
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

	@Test
	public void test2() {
		try {
			/*
			 * get an OM session
			 */
			final SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
					new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

			final org.om.core.api.session.Session session = sessionFactory.getSession(new JcrPersistenceContext(getSessionFactory()));
			Assert.assertNotNull(session);
			/*
			 * make an entity
			 */
			TestEntity testEntity1 = new TestEntity();
			testEntity1.setFoobar("Horray!!");
			testEntity1.setBlargh(1000000);
			testEntity1.setId("foo/bar");
			/*
			 * save it
			 */
			session.save(testEntity1);
			/*
			 * get the entity
			 */
			final TestEntity testEntity2 = session.get(TestEntity.class, "foo/bar");
			Assert.assertNotNull(testEntity2);
			Assert.assertTrue(testEntity2.getId().compareTo("foo/bar") == 0);

			/*
			 * check
			 */
			Assert.assertNotNull(testEntity2.getFoobar());
			Assert.assertTrue(testEntity2.getFoobar().compareTo("Horray!!") == 0);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
