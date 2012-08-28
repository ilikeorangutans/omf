package org.om.core.impl.persistence.jcr;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Repository;

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

	@Test
	public void testSimpleEntity() throws Exception {

		Node node = rootnode.addNode("entity");

		PersistenceContext persistenceContext = new JcrPersistenceContext(jcrSession);
		Session session = sessionFactory.getSession(persistenceContext);

		TestEntity testEntity = session.get(TestEntity.class, "entity");

		assertThat(testEntity, notNullValue());
		// Disabled // ID does not map to a property any more //
		// assertThat(testEntity.getId(), is(""));
		assertThat(testEntity.getBlargh(), is(3131));
	}

	@Test
	public void testSimpleCollectionWithReferenceValues() throws Exception {

		Node node = rootnode.addNode("collection");
		node.addNode("element1").setProperty("value", "first value");
		node.addNode("element2").setProperty("value", "second value");

		Session session = sessionFactory.getSession(new JcrPersistenceContext(jcrSession));
		System.out.println("JcrPersistenceDelegateIT.test() Got session " + session);

		CollectionTestEntity entity = session.get(CollectionTestEntity.class, "collection");

		assertThat(entity, notNullValue());
		List<ChildEntity> list = entity.getList();
		assertThat(list, notNullValue());
		assertThat(list.size(), is(2));

		ChildEntity childEntity = list.get(0);
		assertThat(childEntity, notNullValue());
		assertThat(childEntity.getValue(), is("first value"));

	}

	@Test
	public void testCollectionWithStringValuesFromMultiValueProperty() throws Exception {

		Node node = rootnode.addNode("collection");
		node.setProperty("listOfStrings", new String[] { "first value", "second value" });

		Session session = sessionFactory.getSession(new JcrPersistenceContext(jcrSession));
		System.out.println("JcrPersistenceDelegateIT.test() Got session " + session);

		CollectionTestEntity entity = session.get(CollectionTestEntity.class, "collection");

		assertThat(entity, notNullValue());
		List<String> list = entity.getListOfStrings();
		assertThat(list, notNullValue());
		assertThat(list.size(), is(2));

		assertThat(list, containsInAnyOrder("first value", "second value"));
	}

}
