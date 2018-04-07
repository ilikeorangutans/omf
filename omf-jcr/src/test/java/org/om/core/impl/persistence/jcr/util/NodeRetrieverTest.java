package org.om.core.impl.persistence.jcr.util;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Session;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.om.core.impl.persistence.jcr.test.TransientRepoTestEnv;

import com.google.guiceberry.junit4.GuiceBerryRule;

public class NodeRetrieverTest {
   @Rule
   public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);
   @Inject
   private Session jcrSession;
   private NodeRetriever retriever;

   @Before
   public void setUp() throws Exception {
      retriever = new NodeRetriever(jcrSession);
      jcrSession.getRootNode().addNode("foo").addNode("bar");
   }

   @Test
   public void testAbsolutePath() throws Exception {
      final Node node = retriever.getNode("/foo/bar");
      assertThat(node, notNullValue());
      assertThat(node.getPath(), is("/foo/bar"));
   }

   @Test
   public void testRelativePath() throws Exception {
      // jcrSession.getRootNode().addNode("foo").addNode("bar");
      final Node fooNode = jcrSession.getRootNode().getNode("foo");
      assertThat(fooNode, notNullValue());
      final Node node = retriever.getNode("bar", fooNode);
      assertThat(node, notNullValue());
   }
}
