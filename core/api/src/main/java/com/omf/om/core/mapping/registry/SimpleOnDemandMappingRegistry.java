package com.omf.om.core.mapping.registry;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.registry.MappingRegistry;

/**
 * Simple {@link MappingRegistry} that scans types as they come in.
 * 
 * @author Jakob Külzer
 * 
 */
public class SimpleOnDemandMappingRegistry implements MappingRegistry {

	public SimpleOnDemandMappingRegistry() {
	}

	public EntityMapping getMapping(Class<?> clazz) {

		return null;
	}

}
