package org.om.core.impl.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.junit.Test;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.TransientRepositorySessionFactory;
import org.om.core.impl.session.factory.ImmutableSessionFactory;

public class JackrabbitTest {

	private void recurse(Node node) throws RepositoryException {
		System.out.println("JackrabbitTest.recurse() " + node.getPath());

		if (node.hasNodes()) {
			for (final NodeIterator ni = node.getNodes(); ni.hasNext();) {
				recurse(ni.nextNode());
			}
		}

	}

	@Test
	public void testFoo() throws Exception {

		/**
		 * a convenient session factory
		 */
		final TransientRepositorySessionFactory jcrSessionFactory = new TransientRepositorySessionFactory("target/repository");
		final Session session = jcrSessionFactory.getSession();

		final Node rootNode = session.getRootNode();

		final Node foo = rootNode.addNode("foo");
		final Node bar = foo.addNode("bar");

		bar.setProperty("foobar", "Horray!!");
		bar.setProperty("mycoolfield", "1000000");

		recurse(rootNode);

		final SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
				new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

		final org.om.core.api.session.Session s = sessionFactory.getSession(new JcrPersistenceContext(session));

		final TestEntity testEntity = s.get(TestEntity.class, "foo/bar");

		System.out.println("JackrabbitTest.testFoo() " + testEntity);

		System.out.println("JackrabbitTest.testFoo() " + testEntity.getFoobar());
		System.out.println("JackrabbitTest.testFoo() " + testEntity.getBlargh());
	}

}
