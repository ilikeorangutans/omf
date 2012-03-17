package com.omf.om.core.mapping.registry;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.extractor.EntityMappingExtractor;
import com.omf.om.api.mapping.registry.MappingRegistry;

/**
 * Simple {@link MappingRegistry} that scans types as they come in.
 * 
 * @author Jakob Külzer
 * 
 */
public class OnDemandMappingRegistry implements MappingRegistry {

	private final EntityMappingExtractor entityMappingExtractor;

	public OnDemandMappingRegistry(EntityMappingExtractor entityMappingExtractor) {
		this.entityMappingExtractor = entityMappingExtractor;
	}

	public EntityMapping getMapping(Class<?> clazz) {
		return entityMappingExtractor.extract(clazz);
	}

}
