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
		try {
			Node node = null;
			if (id instanceof String) {
				if (true == context.getSession().getRootNode().hasNode((String) id)) {
					node = context.getSession().getRootNode().getNode((String) id);
				} else if (createNode) {
					node = context.getSession().getRootNode().addNode((String) id);
				}
			} else if (id instanceof Path) {
				if (true == context.getSession().getRootNode().hasNode(((Path) id).toString())) {
					node = context.getSession().getRootNode().getNode(((Path) id).toString());
				} else if (createNode) {
					node = context.getSession().getRootNode().addNode(((Path) id).toString());
				}
			}
			return new JcrPersistenceDelegate(session, mapping, node);
		} catch (final PathNotFoundException e) {
			throw new org.om.core.api.exception.PathNotFoundException(null);
		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Could not create persistence delegate", e);
		}
	}
}
