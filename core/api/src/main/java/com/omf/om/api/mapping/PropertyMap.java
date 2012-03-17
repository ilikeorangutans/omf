package com.omf.om.api.mapping;

import java.util.Collection;

/**
 * Contains all {@link PropertyMapping}s for an {@link EntityMapping}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PropertyMap {

	/**
	 * Returns all {@link PropertyMapping}s.
	 * 
	 * @return
	 */
	Collection<PropertyMapping> getAll();

	/**
	 * Returns true if this map contains a property with the given name.
	 * 
	 * @param name
	 * @return
	 */
	boolean hasProperty(String name);

	/**
	 * Returns true if this map contains a field with the given name.
	 * 
	 * @param name
	 * @return
	 */
	boolean hasField(String name);

	/**
	 * Returns the property that has been marked as the id property.
	 * 
	 * @return
	 */
	PropertyMapping getIdProperty();

	PropertyMapping getProperty(String name);

	PropertyMapping getField(String fieldname);

	int getSize();

	boolean isEmpty();
}
