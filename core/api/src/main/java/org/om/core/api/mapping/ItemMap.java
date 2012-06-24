package org.om.core.api.mapping;

import java.util.Collection;

/**
 * Contains all {@link PropertyMapping}s for an {@link EntityMapping}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface ItemMap {

	/**
	 * Returns all {@link Mapping}s.
	 * 
	 * @return
	 */
	Collection<Mapping> getAll();

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

	/**
	 * Finds a property by name.
	 * 
	 * @param name
	 * @return
	 */
	PropertyMapping getProperty(String name);

	/**
	 * Finds a field by name.
	 * 
	 * @param fieldname
	 * @return
	 */
	Mapping getField(String fieldname);

	int getSize();

	boolean isEmpty();
}
