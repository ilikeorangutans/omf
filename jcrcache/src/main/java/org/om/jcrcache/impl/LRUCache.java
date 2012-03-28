package org.om.jcrcache.impl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author tome
 * 
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	/**
	 * size
	 */
	private final int size;

	/**
	 * ctor
	 */
	public LRUCache(int size) {
		super(size + 1, .75F, true);
		this.size = size;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > size;
	}

}
