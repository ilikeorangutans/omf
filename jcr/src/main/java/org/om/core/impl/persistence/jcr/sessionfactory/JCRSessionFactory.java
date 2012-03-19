package org.om.core.impl.persistence.jcr.sessionfactory;

import javax.jcr.Session;

/**
 * A factory for JCR sessions.
 * 
 * @author tome
 */
public interface JCRSessionFactory {
	/**
	 * get a JCR Session
	 */
	Session getSession();
}
