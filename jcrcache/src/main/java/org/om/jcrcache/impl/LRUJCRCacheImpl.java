package org.om.jcrcache.impl;

import java.util.Collection;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.jcrcache.JCRCache;

/**
 * An LRU cache which wraps a JCR persistence delegate. This class is
 * thread-safe
 * 
 * TODO: We need to evaluate how useful this cache is. It doesn't provide any
 * invalidation mechanisms and it potentially could cause issues in low memory
 * scenarios. IMHO it makes more sense to use weak references. Apart from these
 * issues, a cache on the JCR level doesn't make that much sense as the JCR
 * implementation makes these calls fast enough already.
 * 
 * TODO: If we decide to go with a cache on JCR level, it might make sense to
 * build it on an existing cache layer (Guava for example has excellent caches)
 * instead of building our own.
 * 
 * @author tome
 * @author Jakob Külzer (just comments and some cleanup)
 * 
 */
public class LRUJCRCacheImpl implements JCRCache {
	/**
	 * the actual PersistenceDelegate
	 */
	private final PersistenceAdapter persistenceAdapter;
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
	public LRUJCRCacheImpl(PersistenceAdapter persistenceDelegate, int cacheSize) {
		this.persistenceAdapter = persistenceDelegate;
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
				ret = persistenceAdapter.getProperty(propertyMapping);
			}
			return ret;
		}
	}

	public boolean hasProperty(PropertyMapping mapping) throws ObjectMapperException {
		synchronized (this) {
			/*
			 * delegate
			 */
			return ((LRUJCRCacheImpl) persistenceAdapter).hasProperty(mapping);
		}
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		synchronized (this) {
			if ((null != propertyMapping) && (null != object)) {
				/*
				 * set via the real delegate
				 */
				persistenceAdapter.setProperty(propertyMapping, object);
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
			this.persistenceAdapter.delete();
		}

	}

	public Collection<?> getCollection(CollectionMapping collectionMapping) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean canProvide(Mapping mapping) throws ObjectMapperException {
		// TODO Auto-generated method stub
		return false;
	}
}
