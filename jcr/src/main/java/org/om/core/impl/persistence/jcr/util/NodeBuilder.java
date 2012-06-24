package org.om.core.impl.persistence.jcr.util;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

/**
 * A helper class to build structures of {@link Node}s.
 * 
 * @author Jakob Külzer
 * 
 */
public class NodeBuilder {

	private final Session session;
	private Node node;

	public NodeBuilder(Session session) throws RepositoryException {
		this.session = session;
		node = session.getRootNode();
	}

	public NodeBuilder addNode(String path) throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException,
			RepositoryException {
		node = node.addNode(path);
		return this;
	}
	
	

}
