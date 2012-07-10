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
	MappedField getIdProperty();

	Fields getMappedFields();

	/**
	 * Returns the {@link Mapping} for the given field.
	 * 
	 * @param fieldname
	 * @return
	 */
	Mapping getMappingByField(String fieldname);

	/**
	 * Returns the name of this entity.
	 */
	String getName();

	/**
	 * Returns the class of the mapped type.
	 * 
	 * @return
	 */
	Class<?> getTypeClass();

	/**
	 * Returns true if this entity has a {@link MappedField} with the given
	 * name.
	 * 
	 * @param field
	 * @return
	 */
	boolean hasField(String field);

	/**
	 * Validates the mapping.
	 */
	void validate() throws MappingException;

	/**
	 * Returns a {@link MappedField} by name.
	 * 
	 * @param name
	 */
	MappedField getByFieldName(String name);
}
