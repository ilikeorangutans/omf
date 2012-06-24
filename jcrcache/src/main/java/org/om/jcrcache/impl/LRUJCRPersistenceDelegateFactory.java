package org.om.jcrcache.impl;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.session.Session;

/**
 * 
 * @author tome
 * 
 */
public class LRUJCRPersistenceDelegateFactory implements PersistenceAdapterFactory {

	/**
	 * cache size
	 */
	private final int cacheSize;

	/**
	 * actual delegate factory
	 */
	private final PersistenceAdapterFactory persistenceAdapterFactory;

	/**
	 * ctor
	 */
	public LRUJCRPersistenceDelegateFactory(PersistenceAdapterFactory persistenceAdapterFactory, int cacheSize) {
		this.persistenceAdapterFactory = persistenceAdapterFactory;
		this.cacheSize = cacheSize;
	}

	public PersistenceAdapter create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext, boolean createNode) {
		PersistenceAdapter actualDelegate = persistenceAdapterFactory.create(session, id, mapping, persistenceContext, createNode);
		return new LRUJCRCacheImpl(actualDelegate, cacheSize);
	}

}
