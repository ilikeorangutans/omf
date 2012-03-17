package org.om.core.impl.persistence.jcr;

import java.io.File;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.core.TransientRepository;
import org.junit.Test;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.session.factory.ImmutableSessionFactory;

public class JackrabbitTest {

	@Test
	public void testFoo() throws Exception {
		File file = new File(".");
		System.out.println("JackrabbitTest.testFoo() " + file.getAbsolutePath());
		Repository repository = new TransientRepository(new File(file, "target/repository"));
		System.out.println("JackrabbitTest.testFoo() " + repository);

		Session session = repository.login();

		final Node rootNode = session.getRootNode();

		Node foo = rootNode.addNode("foo");
		Node bar = foo.addNode("bar");

		bar.setProperty("foobar", "Horray!!");
		bar.setProperty("mycoolfield", "1000000");

		recurse(rootNode);

		SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
				new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

		org.om.core.api.session.Session s = sessionFactory.getSession(new JcrPersistenceContext(session));

		TestEntity testEntity = s.get(TestEntity.class, "foo/bar");

		System.out.println("JackrabbitTest.testFoo() " + testEntity);

		System.out.println("JackrabbitTest.testFoo() " + testEntity.getFoobar());
		System.out.println("JackrabbitTest.testFoo() " + testEntity.getBlargh());
	}

	private void recurse(Node node) throws RepositoryException {
		System.out.println("JackrabbitTest.recurse() " + node.getPath());

		if (node.hasNodes()) {
			for (NodeIterator ni = node.getNodes(); ni.hasNext();) {
				recurse(ni.nextNode());
			}
		}

	}

}
