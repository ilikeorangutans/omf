package org.om.core.impl.persistence.jcr;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.entity.ChildEntity;
import org.om.core.impl.entity.CollectionTestEntity;
import org.om.core.impl.entity.TestEntity;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.test.TransientRepoTestEnv;

import com.google.guiceberry.junit4.GuiceBerryRule;

public class JcrPersistenceDelegateIT {

	@Rule
	public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);

	@Inject
	private javax.jcr.Session jcrSession;

	@Inject
	private SessionFactory sessionFactory;

	@Inject
	private Repository repository;

	@Inject
	private JCRSessionFactory jcrSessionFactory;

	private Node rootnode;

	@Before
	public void setUp() throws Exception {
		rootnode = jcrSession.getRootNode();
	}

	@After
	public void tearDown() throws Exception {
		jcrSession.logout();
	}

	@Test
	public void testSimpleEntity() throws Exception {

		Node node = rootnode.addNode("entity");

		PersistenceContext persistenceContext = new JcrPersistenceContext(jcrSession);
		Session session = sessionFactory.getSession(persistenceContext);

		TestEntity testEntity = session.get(TestEntity.class, "entity");

		assertThat(testEntity, notNullValue());
		assertThat(testEntity.getId(), is(""));
		assertThat(testEntity.getBlargh(), is(3131));
	}

	@Test
	public void testSimpleCollection() throws Exception {

		Node node = rootnode.addNode("collection");
		node.addNode("element1").setProperty("value", "first value");
		node.addNode("element2").setProperty("value", "second value");

		Session session = sessionFactory.getSession(new JcrPersistenceContext(jcrSession));
		System.out.println("JcrPersistenceDelegateIT.test() Got session " + session);

		CollectionTestEntity entity = session.get(CollectionTestEntity.class, "collection");

		assertThat(entity, notNullValue());
		List<ChildEntity> list = entity.getList();
		assertThat(list, notNullValue());

	}

}
