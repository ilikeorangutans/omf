package org.om.core.impl.mapping.registry;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;

/**
 * Very, very simple caching registry.
 * 
 * @author Jakob Külzer
 */
public class SimpleCachingOnDemandMappingRegistry extends OnDemandMappingRegistry {

	private final Map<String, EntityMapping> map = Collections.synchronizedMap(new WeakHashMap<String, EntityMapping>());

	public SimpleCachingOnDemandMappingRegistry(EntityMappingExtractor entityMappingExtractor) {
		super(entityMappingExtractor);
	}

	@Override
	public EntityMapping getMapping(Class<?> clazz) {
		if (map.containsKey(clazz.getName()))
			return map.get(clazz.getName());

		final EntityMapping mapping = super.getMapping(clazz);
		map.put(clazz.getName(), mapping);
		return mapping;
	}

}
