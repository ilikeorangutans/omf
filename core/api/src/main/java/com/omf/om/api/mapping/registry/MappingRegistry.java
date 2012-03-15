package com.omf.om.api.mapping.registry;

import com.omf.om.api.mapping.EntityMapping;

/**
 * Keeps track of {@link EntityMapping}s.
 * 
 * @author Jakob Külzer
 * 
 */
public interface MappingRegistry {

	EntityMapping getMapping(Class<?> clazz);

}
