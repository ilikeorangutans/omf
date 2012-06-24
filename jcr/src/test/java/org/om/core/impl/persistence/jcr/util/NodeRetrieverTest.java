package org.om.core.impl.persistence.jcr.util;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.jcr.Node;

import org.junit.Rule;
import org.junit.Test;
import org.om.core.impl.persistence.jcr.test.TransientRepoTestEnv;

import com.google.guiceberry.junit4.GuiceBerryRule;

public class NodeRetrieverTest {

	@Rule
	public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);

	@Inject
	private javax.jcr.Session jcrSession;

	@Test
	public void testAbsolutePath() throws Exception {

		jcrSession.getRootNode().addNode("foo").addNode("bar");

		NodeRetriever retriever = new NodeRetriever(jcrSession);

		Node node = retriever.getNode("/foo/bar");

		assertThat(node, notNullValue());
		assertThat(node.getPath(), is("/foo/bar"));
	}

	@Test
	public void testRelativePath() throws Exception {
		jcrSession.getRootNode().addNode("foo").addNode("bar");
	}
}
