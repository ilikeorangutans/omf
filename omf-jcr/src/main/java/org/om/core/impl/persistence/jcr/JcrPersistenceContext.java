package org.om.core.impl.persistence.jcr;

import javax.jcr.*;

import org.om.core.api.persistence.*;

/**
 * {@link PersistenceContext} for JCR repositories.
 *
 * @author Jakob Külzer
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
