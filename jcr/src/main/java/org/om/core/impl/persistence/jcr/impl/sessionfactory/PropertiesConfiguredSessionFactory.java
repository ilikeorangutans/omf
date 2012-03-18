package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import java.io.InputStream;
import java.util.Properties;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * 
 * @author tome. This is the "PROD" session factory. Note that sessions that
 *         come from here are one-per-thread.
 * 
 */
public class PropertiesConfiguredSessionFactory implements SessionFactory {

	/**
	 * default properties file (
	 */
	private static final String DEFAULT_PROPERTIES_FILE = "/objectmanager.properties";

	/**
	 * propertiesFile
	 */
	private final String propertiesFile;

	/**
	 * ctor
	 */
	public PropertiesConfiguredSessionFactory() {
		propertiesFile = DEFAULT_PROPERTIES_FILE;
	}

	/**
	 * ctor
	 */
	public PropertiesConfiguredSessionFactory(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public Session getSession() throws JCRException {
		try {
			/*
			 * get the props file
			 */
			final InputStream inputStream = PropertiesConfiguredSessionFactory.class.getResourceAsStream(propertiesFile);
			if (null == inputStream) {
				throw new Exception("Unable to find '" + propertiesFile + "'");
			}

			// load props
			final Properties properties = new Properties();
			properties.load(inputStream);

			// go for it
			final Repository repository = JcrUtils.getRepository(properties.getProperty("url"));
			final SimpleCredentials creds = new SimpleCredentials(properties.getProperty("username"), properties.getProperty("password").toCharArray());
			String workspace = null;
			if (properties.contains("workspace")) {
				final String ws = properties.getProperty("workspace").trim();
				if ((null != ws) && (ws.length() > 0)) {
					workspace = ws;
				}
			}
			return repository.login(creds, workspace);
		} catch (final Exception e) {
			throw new JCRException("Exception in doGetSession", e);
		}
	}
}
