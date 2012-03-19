package org.om.dao.config.impl;

import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.dao.config.ObjectManagerConfiguration;

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
	 * JCR session factory
	 */
	private JCRSessionFactory jcrSessionFactory;

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

	public JCRSessionFactory getJCRSessionFactory() throws Exception {
		return jcrSessionFactory;
	}

	public SessionFactory getSession() throws Exception {
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
				 * digester
				 */
				final Digester digester = new Digester();
				digester.addObjectCreate("datasources/datasource", "DataSource");
				digester.addCallMethod("datasources/datasource/name", "setName", 0);
				digester.addCallMethod("datasources/datasource/driver", "setDriver", 0);

				/*
				 * parse XML
				 */
				// ObjectManagerConfig objectManagerConfig =
				// ObjectManagerConfigurationXMLMarshaller.unmarshall(inputStream);
				/*
				 * the JCR factory
				 */
				// this.jcrSessionFactory =
				// createJCRSessionFactory(objectManagerConfig.getJcrfactory());
			} else {
				throw new Exception("Unable to load '" + ObjectManagerConfiguration.DEFAULT_OBJECTMANAGER_CONFIGURATION + "'");
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in parseConfiguration ctor", e);
		}
	}

}
