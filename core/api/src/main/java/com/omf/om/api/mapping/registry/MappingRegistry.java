package com.omf.om.api.mapping.registry;

import com.omf.om.api.mapping.EntityMapping;

/**
 * 
 * @author Jakob Külzer
 * 
 */
public interface MappingRegistry {

	EntityMapping getMapping(Class<?> clazz);

}
