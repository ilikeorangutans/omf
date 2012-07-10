package org.om.core.impl.mapping.registry;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.api.mapping.registry.MappingRegistry;

/**
 * Simple {@link MappingRegistry} that scans types as they come in.
 * 
 * @author Jakob Külzer
 * 
 */
@Component
@Service(value = MappingRegistry.class)
public class OnDemandMappingRegistry implements MappingRegistry {

	private final EntityMappingExtractor entityMappingExtractor;

	@Inject
	public OnDemandMappingRegistry(EntityMappingExtractor entityMappingExtractor) {
		this.entityMappingExtractor = entityMappingExtractor;
	}

	public EntityMapping getMapping(Class<?> clazz) {
		return entityMappingExtractor.extract(clazz);
	}

}
