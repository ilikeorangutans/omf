package org.om.core.api.persistence.result;

import org.om.core.api.persistence.PersistenceAdapter;

/**
 * Result from a {@link PersistenceAdapter}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PersistenceResult {

	/**
	 * The actual result object.
	 * 
	 * @return
	 */
	Object getValue();

	/**
	 * Returns true if any result was retrieved. This will return true even if
	 * the result was null. If the underlying persistence mechanism can't find
	 * the requested entity, it should set this flag to false. It is up to to
	 * the calling {@link PersistenceAdapter} how to react to this.
	 * 
	 * @return
	 */
	boolean hasResult();

}
