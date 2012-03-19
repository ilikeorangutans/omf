package org.om.dao.genericdao.impl;

import java.util.UUID;

import org.om.core.api.path.Path;
import org.om.core.api.session.Session;
import org.om.dao.genericdao.GenericDAO;
import org.om.dao.util.SessionUtil;

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
		try {
			SessionUtil.getSession();
		} catch (final Exception e) {
			throw new Exception("Exception in delete", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.om.core.impl.persistence.jcr.dao.GenericJCRDAO#get(java.lang.String)
	 */
	public T get(Path path) throws Exception {
		Session session = null;
		try {
			session = SessionUtil.getSession();
			return session.get(persistentClass, path);
		} catch (final Exception e) {
			throw new Exception("Exception in get", e);
		}
	}

	public T get(UUID uuid) throws Exception {
		Session session = null;
		try {
			session = SessionUtil.getSession();
			return session.get(persistentClass, uuid);
		} catch (final Exception e) {
			throw new Exception("Exception in get", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.om.core.impl.persistence.jcr.dao.GenericJCRDAO#save(T)
	 */
	public void save(T t) throws Exception {
		try {
			SessionUtil.getSession();
		} catch (final Exception e) {
			throw new Exception("Exception in save", e);
		}
	}
}
