package org.om.core.impl.persistence.jcr;

import java.util.UUID;

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

public class JcrPersistenceDelegateFactory implements PersistenceDelegateFactory {

	@SuppressWarnings("deprecation")
	public PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext) {
		final JcrPersistenceContext context = (JcrPersistenceContext) persistenceContext;
		try {
			Node node = null;
			if (id instanceof String) {
				node = context.getSession().getRootNode().getNode((String) id);
			} else if (id instanceof Path) {
				node = context.getSession().getRootNode().getNode(((Path) id).toString());
			} else if (id instanceof UUID) {
				String uuid = ((UUID) id).toString();
				node = context.getSession().getNodeByUUID(uuid);
			}
			return new JcrPersistenceDelegate(session, mapping, node);
		} catch (final PathNotFoundException e) {
			throw new org.om.core.api.exception.PathNotFoundException(null);
		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Could not create persistence delegate", e);
		}
	}
}
