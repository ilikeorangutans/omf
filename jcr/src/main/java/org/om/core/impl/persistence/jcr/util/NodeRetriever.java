package org.om.core.impl.persistence.jcr.util;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Retrieves nodes from a JCR session.
 * 
 * @author Jakob Külzer
 * 
 */
public class NodeRetriever {

	private final Session session;

	public NodeRetriever(Session session) {
		this.session = session;
	}

	public Node getNode(String path) throws RepositoryException {
		return getNode(path, session.getRootNode());
	}

	public Node getNode(String path, Node context) throws RepositoryException {
		boolean absolutePath = path.startsWith("/");
		if (absolutePath) {
			context = session.getRootNode();
			path = path.substring(1);
		}

		return context.getNode(path);
	}

}
