package org.om.core.impl.persistence.jcr.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.inject.*;
import javax.jcr.*;

import org.junit.*;
import org.om.core.impl.persistence.jcr.test.*;

import com.google.guiceberry.junit4.*;

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
