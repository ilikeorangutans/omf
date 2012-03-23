package org.om.jcrcache.impl;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.jcrcache.JCRCache;

/**
 * An LRU cache which wraps a JCR persistence delegate. This class is
 * thread-safe
 * 
 * @author tome
 * 
 */
public class LRUJCRCacheImpl implements JCRCache {
	/**
	 * the actual PersistenceDelegate
	 */
	private final PersistenceDelegate persistenceDelegate;
	/**
	 * the cache
	 */
	private final LRUCache<String, Object> cache;

	/**
	 * cache size
	 */
	private final int cacheSize;

	/**
	 * ctor
	 */
	public LRUJCRCacheImpl(PersistenceDelegate persistenceDelegate, int cacheSize) {
		this.persistenceDelegate = persistenceDelegate;
		this.cacheSize = cacheSize;
		cache = new LRUCache<String, Object>(this.cacheSize);
	}

	private String getKey(PropertyMapping mapping) throws ObjectMapperException {
		return mapping.getFieldname();
	}

	public Object getProperty(PropertyMapping propertyMapping) throws ObjectMapperException {
		synchronized (this) {
			/*
			 * search the cache
			 */
			Object ret = cache.get(getKey(propertyMapping));
			if (null == ret) {
				/*
				 * fine, use the delegate
				 */
				ret = persistenceDelegate.getProperty(propertyMapping);
			}
			return ret;
		}
	}

	public boolean hasProperty(PropertyMapping mapping) throws ObjectMapperException {
		synchronized (this) {
			/*
			 * delegate
			 */
			return persistenceDelegate.hasProperty(mapping);
		}
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		synchronized (this) {
			if ((null != propertyMapping) && (null != object)) {
				/*
				 * set via the real delegate
				 */
				persistenceDelegate.setProperty(propertyMapping, object);
				/*
				 * item is cached?
				 */
				if (cache.containsKey(getKey(propertyMapping))) {
					/*
					 * remove
					 */
					cache.remove(getKey(propertyMapping));
				}
				/*
				 * cache
				 */
				cache.put(getKey(propertyMapping), object);
			}
		}
	}

	public int size() {
		return cache.size();
	}

	public void delete() throws ObjectMapperException {
		synchronized (this) {
			/*
			 * remove the cache
			 */
			cache.clear();
			/*
			 * delegate
			 */
			this.persistenceDelegate.delete();
		}

	}
}
