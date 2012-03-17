package org.om.core.impl.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.PersistenceDelegateFactory;
import org.om.core.api.session.Session;

public class JcrPersistenceDelegateFactory implements PersistenceDelegateFactory {

	public PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext) {
		final JcrPersistenceContext context = (JcrPersistenceContext) persistenceContext;

		try {
			final Node node = context.getSession().getRootNode().getNode((String) id);
			return new JcrPersistenceDelegate(session, mapping, node);
		} catch (PathNotFoundException e) {
			throw new org.om.core.api.exception.PathNotFoundException(null);
		} catch (RepositoryException e) {
			throw new ObjectMapperException("Could not create persistence delegate", e);
		}
	}

}
