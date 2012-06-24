package org.om.core.api.persistence.interceptor.handler;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.persistence.PersistenceAdapter;

/**
 * Handles the actual translation from the storage repository provided value
 * into an object of the appropriate type as described by the {@link Mapping}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface ItemHandler {

	/**
	 * Retrieves the object described by the mapping form the given
	 * {@link PersistenceAdapter}.
	 * 
	 * @param propertyMapping
	 * @param input
	 * @return
	 */
	Object retrieve(Mapping mapping, PersistenceAdapter delegate);

}
