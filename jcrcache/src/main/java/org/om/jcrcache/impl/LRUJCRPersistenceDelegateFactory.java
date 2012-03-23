package org.om.jcrcache.impl;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.PersistenceDelegateFactory;
import org.om.core.api.session.Session;

/**
 * 
 * @author tome
 * 
 */
public class LRUJCRPersistenceDelegateFactory implements PersistenceDelegateFactory {

	/**
	 * cache size
	 */
	private final int cacheSize;

	/**
	 * actual delegate factory
	 */
	private final PersistenceDelegateFactory persistenceDelegateFactory;

	/**
	 * ctor
	 */
	public LRUJCRPersistenceDelegateFactory(PersistenceDelegateFactory persistenceDelegateFactory, int cacheSize) {
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.cacheSize = cacheSize;
	}

	public PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext, boolean createNode) {
		PersistenceDelegate actualDelegate = persistenceDelegateFactory.create(session, id, mapping, persistenceContext, createNode);
		return new LRUJCRCacheImpl(actualDelegate, cacheSize);
	}

}
