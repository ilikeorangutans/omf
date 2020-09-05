package org.om.core.impl.persistence.jcr.util;

import javax.jcr.*;
import javax.jcr.lock.*;
import javax.jcr.nodetype.*;
import javax.jcr.version.*;

/**
 * A helper class to build structures of {@link Node}s.
 *
 * @author Jakob Külzer
 */
public class NodeBuilder {
	private Node node;

	public NodeBuilder(Session session) throws RepositoryException {
		node = session.getRootNode();
	}

	public NodeBuilder addNode(String path) throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException, RepositoryException {
		node = node.addNode(path);
		return this;
	}
}
