/*
 * Copyright 2012 Jakob Külzer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.om.core.impl.persistence.jcr;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

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
import org.om.core.impl.entity.EntityWithMaps;
import org.om.core.impl.entity.TestEntity;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.test.TransientRepoTestEnv;

import com.google.guiceberry.junit4.GuiceBerryRule;

public class JcrPersistenceDelegate {

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

	/**
	 * Testcase for retrieving {@link Map}s built of a node's properties.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMapWithStringValues() throws Exception {
		Node node = rootnode.addNode("node").addNode("mapFromProperties");

		node.setProperty("foo", "bar");
		node.setProperty("chicken", "soup");

		Session session = sessionFactory.getSession(new JcrPersistenceContext(jcrSession));

		EntityWithMaps entityWithMaps = session.get(EntityWithMaps.class, "/node");

		Map<String, String> map = entityWithMaps.getMap();
		assertThat(map, notNullValue());
		assertThat(map.size(), is(2));

		assertThat(map.containsKey("chicken"), is(true));
		assertThat(map.containsKey("foo"), is(true));
	}
}
