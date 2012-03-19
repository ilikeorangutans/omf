package org.om.core.impl.persistence.jcr.sessionfactory.impl;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.exception.JCRException;
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

	public Session getSession() throws JCRException {
		try {
			throw new Exception("Not Implemented");
		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
