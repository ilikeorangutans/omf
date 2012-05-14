package org.om.core.impl.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.path.Path;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.PersistenceDelegateFactory;
import org.om.core.api.session.Session;

/**
 * @author Jakob Külzer
 * @author tome
 * 
 */
public class JcrPersistenceDelegateFactory implements PersistenceDelegateFactory {

	public PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext, boolean createNode) {
		final JcrPersistenceContext context = (JcrPersistenceContext) persistenceContext;
		final String path;
		if (id instanceof String) {
			path = (String) id;
		} else if (id instanceof Path) {
			path = ((Path) id).toString();
		} else {
			throw new IllegalArgumentException();
		}

		try {

			final Node rootNode = context.getSession().getRootNode();
			Node node = null;

			node = rootNode.getNode(path);
			// if (true == rootNode.hasNode(path)) {
			// } else if (createNode) {
			// TODO: If the node cannot be found, we should return a proxy
			// object that records all changes and can be flushed if the
			// transaction is committed.
			// node = rootNode.addNode(path);
			// }

			return new JcrPersistenceDelegate(session, mapping, node);
		} catch (final PathNotFoundException e) {
			throw new org.om.core.api.exception.PathNotFoundException(new Path(path));
		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Could not create persistence delegate", e);
		}
	}
}
