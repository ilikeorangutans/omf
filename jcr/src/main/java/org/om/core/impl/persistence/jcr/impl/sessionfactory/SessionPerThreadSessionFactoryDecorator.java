package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * 
 * @author tome
 * 
 */
public class SessionPerThreadSessionFactoryDecorator implements SessionFactory {

	/**
	 * the session factory to decorate
	 */
	private final SessionFactory sessionFactory;
	/**
	 * the threadlocal primary session
	 */
	private static ThreadLocal<Session> threadLocalSession = null;

	/**
	 * ctor
	 */
	public SessionPerThreadSessionFactoryDecorator(SessionFactory sessionFactory) {
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
		} catch (Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
