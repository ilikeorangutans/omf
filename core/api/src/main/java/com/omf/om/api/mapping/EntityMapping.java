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

	/**
	 * Returns true if this entity mapping has a property with the given name.
	 * 
	 * @param fieldName
	 * @return
	 */
	boolean hasProperty(String property);

	/**
	 * Returns true if this entity has a {@link PropertyMapping} for the given
	 * field.
	 * 
	 * @param field
	 * @return
	 */
	boolean hasField(String field);

	PropertyMapping getPropertyByField(String fieldname);

}
