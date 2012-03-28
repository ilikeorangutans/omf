package org.om.core.api.session;

import org.om.core.api.exception.ObjectMapperException;

/**
 * A single persistence session. A session acts as factory for entities.
 * 
 * Sessions are not threadsafe, they are meant to be used by a single thread
 * only.
 * 
 * @author Jakob Külzer
 * @author tom
 * 
 */
public interface Session {

	/**
	 * get an object by id
	 */
	<T> T get(Class<T> clazz, Object id) throws ObjectMapperException;

	/**
	 * Closes the session.
	 * 
	 * TODO: What does this actually mean? Will the session not be able to
	 * produce further objects? Will it close the underlying persistence layer?
	 * We need to define the semantics.
	 */
	void close() throws ObjectMapperException;

	/**
	 * save
	 */
	void save(Object o) throws ObjectMapperException;

	/**
	 * delete
	 */
	void delete(Object o) throws ObjectMapperException;

}
