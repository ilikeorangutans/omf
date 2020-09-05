package org.om.core.impl.mapping.registry;

import java.util.*;

import org.om.core.api.mapping.*;
import org.om.core.api.mapping.extractor.*;

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
		if (map.containsKey(clazz.getName())) {
			return map.get(clazz.getName());
		}
		final EntityMapping mapping = super.getMapping(clazz);
		map.put(clazz.getName(), mapping);
		return mapping;
	}
}
