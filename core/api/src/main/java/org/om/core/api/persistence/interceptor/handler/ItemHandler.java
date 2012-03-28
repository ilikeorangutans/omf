package org.om.core.api.persistence.interceptor.handler;

import org.om.core.api.mapping.Mapping;

/**
 * Handles the actual translation from the storage repository provided value
 * into an object of the appropriate type as described by the {@link Mapping}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface ItemHandler {

	/**
	 * Handles the given input value.
	 * 
	 * @param propertyMapping
	 * @param input
	 * @return
	 */
	Object retrieve(Mapping mapping, Object input);

}
