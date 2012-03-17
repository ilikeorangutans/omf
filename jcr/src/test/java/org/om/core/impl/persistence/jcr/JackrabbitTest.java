package org.om.core.impl.persistence.jcr;

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

	@Test
	public void testFoo() throws Exception {

		/**
		 * a convenient session factory
		 */
		final TransientRepositorySessionFactory jcrSessionFactory = new TransientRepositorySessionFactory("target/repository");
		final Session session = jcrSessionFactory.getSession();

		/**
		 * test some OM stuff
		 */
		final SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
				new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

		final org.om.core.api.session.Session s = sessionFactory.getSession(new JcrPersistenceContext(session));

		final TestEntity testEntity = s.get(TestEntity.class, "foo/bar");

		System.out.println("JackrabbitTest.testFoo() " + testEntity);

		System.out.println("JackrabbitTest.testFoo() " + testEntity.getFoobar());
		System.out.println("JackrabbitTest.testFoo() " + testEntity.getBlargh());
	}

}
