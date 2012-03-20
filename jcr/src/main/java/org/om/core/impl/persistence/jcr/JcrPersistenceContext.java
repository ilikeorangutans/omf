package org.om.core.impl.persistence.jcr;

import javax.jcr.Session;

import org.om.core.api.persistence.PersistenceContext;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

/**
 * {@link PersistenceContext} for JCR repositories.
 * 
 * @author Jakob Külzer
 * 
 */
public class JcrPersistenceContext implements PersistenceContext {

	private final JCRSessionFactory jcrSessionFactory;

	public JcrPersistenceContext(JCRSessionFactory jcrSessionFactory) {
		this.jcrSessionFactory = jcrSessionFactory;
	}

	public Session getSession() {
		return jcrSessionFactory.getSession();
	}

}
