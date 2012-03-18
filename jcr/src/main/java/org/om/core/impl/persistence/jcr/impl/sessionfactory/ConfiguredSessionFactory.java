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
public class ConfiguredSessionFactory implements SessionFactory {

	/**
	 * the threadlocal primary session
	 */
	private static ThreadLocal<Session> threadLocalSession = null;

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
	public ConfiguredSessionFactory() {
		propertiesFile = DEFAULT_PROPERTIES_FILE;
	}

	/**
	 * ctor
	 */
	public ConfiguredSessionFactory(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public Session getSession() throws JCRException {
		try {
			/*
			 * create if needed
			 */
			if (null == threadLocalSession) {
				/*
				 * get the session for this thread
				 */
				final Session session = doGetSession();
				/*
				 * check
				 */
				if (null != session) {
					/*
					 * create the var
					 */
					threadLocalSession = new ThreadLocal<Session>();
					/*
					 * set
					 */
					threadLocalSession.set(session);
				}
			}
			/*
			 * return
			 */
			if (null != threadLocalSession) {
				return threadLocalSession.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}

	private Session doGetSession() throws JCRException {
		try {
			/*
			 * get the props file
			 */
			final InputStream inputStream = ConfiguredSessionFactory.class.getResourceAsStream(propertiesFile);
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
