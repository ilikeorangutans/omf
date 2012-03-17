package org.om.core.impl.mapping.registry;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.api.mapping.registry.MappingRegistry;

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
