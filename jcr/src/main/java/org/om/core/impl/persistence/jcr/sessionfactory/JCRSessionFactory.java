package org.om.core.impl.persistence.jcr.sessionfactory;

import javax.jcr.Session;

/**
 * A factory for JCR sessions.
 * 
 * @author Jakob Külzer
 * @author tome
 */
public interface JCRSessionFactory {

	/**
	 * Returns a JCR Session.
	 */
	Session getSession();
}
