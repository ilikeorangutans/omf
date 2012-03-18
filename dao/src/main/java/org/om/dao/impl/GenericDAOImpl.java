package org.om.dao.impl;

import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.core.impl.persistence.jcr.JcrPersistenceDelegateFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.ConfiguredSessionFactory;
import org.om.core.impl.session.factory.ImmutableSessionFactory;
import org.om.dao.api.GenericDAO;

/**
 * @author tome
 */
public class GenericDAOImpl<T> implements GenericDAO<T> {
	/**
	 * the class
	 */
	private final Class<T> persistentClass;

	/**
	 * ctor
	 */
	public GenericDAOImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.om.core.impl.persistence.jcr.dao.GenericJCRDAO#delete(T)
	 */
	public void delete(T t) throws Exception {
		Session session = null;
		try {
			session = getSession();
		} catch (final Exception e) {
			throw new Exception("Exception in delete", e);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.om.core.impl.persistence.jcr.dao.GenericJCRDAO#get(java.lang.String)
	 */
	public T get(String jcrPath) throws Exception {
		Session session = null;
		try {
			session = getSession();
			return session.get(persistentClass, jcrPath);
		} catch (final Exception e) {
			throw new Exception("Exception in get", e);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	/**
	 * get OM session
	 */
	private Session getSession() throws JCRException {
		try {
			/*
			 * test some OM stuff
			 */
			final SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
					new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

			return sessionFactory.getSession(new JcrPersistenceContext(new ConfiguredSessionFactory().getSession()));

		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.om.core.impl.persistence.jcr.dao.GenericJCRDAO#save(T)
	 */
	public void save(T t) throws Exception {
		Session session = null;
		try {
			session = getSession();
		} catch (final Exception e) {
			throw new Exception("Exception in save", e);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
}
