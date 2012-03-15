package com.omf.om.core.persistence.jcr;

import javax.jcr.Session;

import com.omf.om.api.persistence.PersistenceContext;

public class JcrPersistenceContext implements PersistenceContext {

	private final Session session;

	public JcrPersistenceContext(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

}
