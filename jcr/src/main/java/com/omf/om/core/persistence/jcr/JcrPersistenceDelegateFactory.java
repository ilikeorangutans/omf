package com.omf.om.core.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import com.omf.om.api.exception.ObjectMapperException;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.PersistenceDelegateFactory;
import com.omf.om.api.session.Session;

public class JcrPersistenceDelegateFactory implements PersistenceDelegateFactory {

	public PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext) {
		final JcrPersistenceContext context = (JcrPersistenceContext) persistenceContext;

		try {
			final Node node = context.getSession().getRootNode().getNode((String) id);
			return new JcrPersistenceDelegate(session, mapping, node);
		} catch (PathNotFoundException e) {
			throw new com.omf.om.api.exception.PathNotFoundException(null);
		} catch (RepositoryException e) {
			throw new ObjectMapperException("Could not create persistence delegate", e);
		}
	}

}
