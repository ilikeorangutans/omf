package org.om.dao.config.impl;

import java.io.InputStream;

import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.dao.config.ObjectManagerConfiguration;

import com.khubla.xmlautowire.AutowireBeanRegistry;
import com.khubla.xmlautowire.impl.DefaultAutowireBeanRegistry;

/**
 * 
 * @author tome
 * 
 */
public class XMLObjectManagerConfiguration implements ObjectManagerConfiguration {

	/**
	 * singleton getter
	 */
	public static ObjectManagerConfiguration getObjectManagerConfiguration() throws JCRException {
		if (null == instance) {
			instance = new XMLObjectManagerConfiguration();
		}
		return instance;
	}

	/**
	 * OM session factory
	 */
	private SessionFactory sessionFactory;

	/**
	 * persistence context
	 */
	private JcrPersistenceContext jcrPersistenceContext;

	/**
	 * singleton
	 */
	private static XMLObjectManagerConfiguration instance = null;

	/**
	 * private ctor
	 */
	private XMLObjectManagerConfiguration() throws JCRException {
		try {
			parseConfiguration();
		} catch (final Exception e) {
			throw new JCRException("Exception in XMLObjectManagerConfiguration ctor", e);
		}
	}

	public JcrPersistenceContext getJcrPersistenceContext() throws Exception {
		return jcrPersistenceContext;
	}

	public SessionFactory getSessionFactory() throws Exception {
		return sessionFactory;
	}

	/**
	 * parse the XML configuration
	 */
	private void parseConfiguration() throws JCRException {
		try {
			/*
			 * get file
			 */
			final InputStream inputStream = XMLObjectManagerConfiguration.class
					.getResourceAsStream(ObjectManagerConfiguration.DEFAULT_OBJECTMANAGER_CONFIGURATION);
			if (null != inputStream) {
				/*
				 * dynamic xml beans
				 */
				final AutowireBeanRegistry dynabeanRegistry = new DefaultAutowireBeanRegistry();
				dynabeanRegistry.load(inputStream);
				/*
				 * populate the beans we need
				 */
				jcrPersistenceContext = (JcrPersistenceContext) dynabeanRegistry.find("jcrpersistencecontext");
				sessionFactory = (SessionFactory) dynabeanRegistry.find("omsessionfactory");
			} else {
				throw new Exception("Unable to load '" + ObjectManagerConfiguration.DEFAULT_OBJECTMANAGER_CONFIGURATION + "'");
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in parseConfiguration ctor", e);
		}
	}
}
