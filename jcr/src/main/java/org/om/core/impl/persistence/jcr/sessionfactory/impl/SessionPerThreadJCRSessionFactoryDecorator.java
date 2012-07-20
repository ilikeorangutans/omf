package org.om.core.impl.persistence.jcr.sessionfactory.impl;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class SessionPerThreadJCRSessionFactoryDecorator implements JCRSessionFactory {

	/**
	 * the threadlocal primary session
	 */
	private static ThreadLocal<Session> threadLocalSession = null;
	/**
	 * the session factory to decorate
	 */
	private final JCRSessionFactory sessionFactory;

	/**
	 * ctor
	 */
	public SessionPerThreadJCRSessionFactoryDecorator(JCRSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

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
				final Session session = sessionFactory.getSession();
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
		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
