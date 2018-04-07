package org.om.core.impl.persistence.jcr;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.jcr.Node;

import org.junit.Rule;
import org.junit.Test;
import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.entity.EntityWithReference;
import org.om.core.impl.entity.TestEntity;
import org.om.core.impl.persistence.jcr.test.TransientRepoTestEnv;

import com.google.guiceberry.junit4.GuiceBerryRule;

/**
 * Tests the entire stack by retrieving entities from the JCR.
 *
 * @author Jakob Külzer
 */
public class JcrEntityAccessTest {
   @Rule
   public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);
   @Inject
   private javax.jcr.Session jcrSession;
   @Inject
   private SessionFactory sessionFactory;

   @Test(expected = RuntimeException.class)
   public void testRetrieveEntityWithInvalidReference() throws Exception {
      final Node rootNode = jcrSession.getRootNode();
      final Node foo = rootNode.addNode("foo");
      foo.setProperty("testEntity", "/i/do/not/exist");
      final Session session = sessionFactory.getSession(new JcrPersistenceContext(jcrSession));
      final EntityWithReference entity = session.get(EntityWithReference.class, "/foo");
      assertThat(entity, notNullValue());
      assertThat("Default behavior is to return null on missing properties", entity.getTestEntity(), nullValue());
      entity.getReferenceThrowingExceptionOnMissing();
   }

   @Test
   public void testRetrieveEntityWithReference() throws Exception {
      final Node rootNode = jcrSession.getRootNode();
      final Node foo = rootNode.addNode("foo");
      foo.setProperty("testEntity", "/foo/testEntity");
      final Node child = foo.addNode("testEntity");
      child.setProperty("foobar", "I like pie!");
      final Session session = sessionFactory.getSession(new JcrPersistenceContext(jcrSession));
      final EntityWithReference entity = session.get(EntityWithReference.class, "/foo");
      assertThat(entity, notNullValue());
      assertThat(entity.getTestEntity(), notNullValue());
      assertThat(entity.getTestEntity().getFoobar(), is("I like pie!"));
   }

   @Test
   public void testRetrieveTestEntity() throws Exception {
      final Node rootNode = jcrSession.getRootNode();
      final Node foo = rootNode.addNode("foo");
      final Node bar = foo.addNode("bar");
      bar.setProperty("foobar", "Horray!!");
      bar.setProperty("mycoolfield", "1000000");
      final Session session = sessionFactory.getSession(new JcrPersistenceContext(jcrSession));
      final TestEntity testEntity = session.get(TestEntity.class, "foo/bar");
      assertThat(testEntity, notNullValue());
      assertThat(testEntity.getFoobar(), is("Horray!!"));
      assertThat(testEntity.getBlargh(), is(1000000));
   }
}
