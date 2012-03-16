package com.test;

import java.io.File;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.core.TransientRepository;
import org.junit.Test;

import com.omf.om.api.session.factory.SessionFactory;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.mapping.registry.OnDemandMappingRegistry;
import com.omf.om.core.persistence.cglib.CglibProxyFactory;
import com.omf.om.core.persistence.jcr.JcrPersistenceContext;
import com.omf.om.core.persistence.jcr.JcrPersistenceDelegateFactory;
import com.omf.om.core.session.factory.ImmutableSessionFactory;

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

		recurse(rootNode);

		SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
				new EntityMappingExtractorImpl()), new CglibProxyFactory());

		com.omf.om.api.session.Session s = sessionFactory.getSession(new JcrPersistenceContext(session));

		TestEntity testEntity = s.get(TestEntity.class, "foo/bar");

		System.out.println("JackrabbitTest.testFoo() " + testEntity);

		System.out.println("JackrabbitTest.testFoo() " + testEntity.getFoobar());

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
