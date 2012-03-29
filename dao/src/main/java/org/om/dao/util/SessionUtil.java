package org.om.dao.util;

import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.dao.config.ObjectManagerConfiguration;
import org.om.dao.config.impl.XMLObjectManagerConfiguration;

/**
 * 
 * @author tome
 * 
 */
public class SessionUtil {

	/**
	 * the OM config
	 */
	private static ObjectManagerConfiguration objectManagerConfiguration = null;

	/**
	 * get OM session
	 */
	public static Session getSession() throws JCRException {
		try {
			/*
			 * init if needed
			 */
			init();
			/*
			 * the sessionfactory
			 */
			final SessionFactory sessionFactory = objectManagerConfiguration.getSessionFactory();
			/*
			 * session, via the JCR context
			 */
			return sessionFactory.getSession(objectManagerConfiguration.getJcrPersistenceContext());

		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}

	/**
	 * get a JCR context
	 */
	public static JcrPersistenceContext getJcrPersistenceContext() throws JCRException {
		try {
			/*
			 * init if needed
			 */
			init();
			/*
			 * session, via the JCR context
			 */
			return objectManagerConfiguration.getJcrPersistenceContext();
		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}

	/**
	 * init
	 */
	private static void init() throws JCRException {
		try {
			if (null == objectManagerConfiguration) {
				objectManagerConfiguration = XMLObjectManagerConfiguration.getObjectManagerConfiguration();
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in init", e);
		}
	}
}
