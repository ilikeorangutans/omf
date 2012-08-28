package org.om.core.impl.persistence.jcr.sessionfactory.impl;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.exception.JcrException;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class JNDIJCRSessionFactory implements JCRSessionFactory {
	/**
	 * ctor
	 */
	public JNDIJCRSessionFactory(String jndiName) {
	}

	public Session getSession() throws JcrException {
		try {
			throw new Exception("Not Implemented");
		} catch (final Exception e) {
			throw new JcrException("Exception in getSession", e);
		}
	}
}
