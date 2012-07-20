package org.om.core.api.mapping;

import org.om.core.api.annotation.Entity;
import org.om.core.api.exception.MappingException;

/**
 * Describes the mapping for an {@link Entity}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface EntityMapping {

	/**
	 * Returns the {@link PropertyMapping} for the identifier field.
	 * 
	 * @return
	 */
	PropertyMapping getIdProperty();

	ItemMap getItemMappings();

	/**
	 * Returns the {@link PropertyMapping} for the given field.
	 * 
	 * @param fieldname
	 * @return
	 */
	Mapping getMappingByField(String fieldname);

	/**
	 * name
	 */
	String getName();

	/**
	 * Returns the class of the mapped type.
	 * 
	 * @return
	 */
	Class<?> getTypeClass();

	/**
	 * Returns true if this entity has a {@link PropertyMapping} for the given
	 * field.
	 * 
	 * @param field
	 * @return
	 */
	boolean hasField(String field);

	/**
	 * Returns true if this entity mapping has a property with the given name.
	 * 
	 * @param fieldName
	 * @return
	 */
	boolean hasProperty(String property);

	/**
	 * Validates the mapping.
	 */
	void validate() throws MappingException;
}
