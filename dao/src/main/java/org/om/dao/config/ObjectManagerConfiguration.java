package org.om.dao.config;

import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;

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
	 * get JCR persistence context
	 */
	public JcrPersistenceContext getJcrPersistenceContext() throws Exception;

	/**
	 * get a fully configured OM session factory
	 */
	public SessionFactory getSessionFactory() throws Exception;
}
