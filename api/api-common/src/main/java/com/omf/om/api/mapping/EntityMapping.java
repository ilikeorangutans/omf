package com.omf.om.api.mapping;

import java.util.Set;

import com.omf.om.api.annotation.Entity;

/**
 * Describes the mapping for an {@link Entity}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface EntityMapping {

	/**
	 * Returns the class of the mapped type.
	 * 
	 * @return
	 */
	Class<?> getTypeClass();

	Set<PropertyMapping> getPropertyMappings();

}
