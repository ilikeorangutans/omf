package org.om.core.impl.persistence.jcr.sessionfactory.impl;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

/**
 * A decorator that makes the session a singleton. Not useful in production, but
 * very useful when using the transient repository in unit tests
 * 
 * @author tome
 * 
 */
public class SingletonJCRSessionFactoryDecorator implements JCRSessionFactory {

	/**
	 * the session
	 */
	private static Session session = null;

	/**
	 * the session factory to decorate
	 */
	private final JCRSessionFactory sessionFactory;

	/**
	 * ctor
	 */
	public SingletonJCRSessionFactoryDecorator(JCRSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public Session getSession() throws JCRException {
		try {
			/*
			 * create if needed
			 */
			if (null == session) {
				session = sessionFactory.getSession();

			}
			return session;
		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
