package org.om.core.impl.persistence.jcr;

import javax.jcr.Session;

import org.om.core.api.persistence.PersistenceContext;

/**
 * {@link PersistenceContext} for JCR repositories.
 * 
 * @author Jakob Külzer
 * 
 */
public class JcrPersistenceContext implements PersistenceContext {

	private final Session session;

	public JcrPersistenceContext(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

}
