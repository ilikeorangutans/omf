package org.om.core.impl.persistence.jcr.api.config;

import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.api.sessionfactory.JCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public interface ObjectManagerConfiguration {
	/**
	 * the default file
	 */
	public final static String DEFAULT_OBJECTMANAGER_CONFIGURATION = "/objectmanager.xml";

	/**
	 * get a fully configured OM session
	 */
	public SessionFactory getSession() throws Exception;

	/**
	 * get a JCR sesssion
	 */
	public JCRSessionFactory getJCRSessionFactory() throws Exception;
}
