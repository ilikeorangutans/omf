package com.omf.om.api.mapping;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class CachingMappingFactory extends OnDemandMappingFactory {

	private final Map<Class<?>, WeakReference<EntityMapping>> cache;

	public CachingMappingFactory() {
		cache = new HashMap<Class<?>, WeakReference<EntityMapping>>();
	}

	@Override
	public EntityMapping getMapping(Class<?> clazz) {
		if (cache.containsKey(clazz) && cache.get(clazz).get() != null)
			return (EntityMapping) cache.get(clazz);

		EntityMapping mapping = super.getMapping(clazz);
		cache.put(clazz, new WeakReference<EntityMapping>(mapping));

		return mapping;
	}

	@Override
	public String toString() {
		return "CachingMappingFactory [cache=" + cache + "]";
	}

}
