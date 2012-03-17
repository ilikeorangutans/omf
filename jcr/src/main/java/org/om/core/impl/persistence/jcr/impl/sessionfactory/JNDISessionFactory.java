package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * 
 * @author tome
 * 
 */
public class JNDISessionFactory implements SessionFactory {
	/**
	 * JNDI Name
	 */
	private final String jndiName;

	/**
	 * ctor
	 */
	public JNDISessionFactory(String jndiName) {
		this.jndiName = jndiName;
	}

	public Session getSession() throws JCRException {
		try {
			throw new Exception("Not Implemented");
		} catch (Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
