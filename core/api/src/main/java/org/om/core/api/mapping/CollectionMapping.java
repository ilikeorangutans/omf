package org.om.core.api.mapping;

/**
 * Describes the mapping of a collection to a persistence backend.
 * 
 * @author Jakob Külzer
 * 
 */
public interface CollectionMapping extends Mapping {

	/**
	 * Returns the location that backs this collection.
	 * 
	 * @return
	 */
	String getLocation();

}
