package org.om.core.api.session;

import org.om.core.api.exception.MappingException;
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
	 * Saves the given instance. If the class describing the object is not a
	 * mapped entity, this method will throw an exception.
	 * 
	 * TODO: This is a modifying operation, so the implications of transaction
	 * handling have to be declared.
	 * 
	 * @throws MappingException
	 */
	void save(Object o) throws ObjectMapperException, MappingException;

	/**
	 * Deletes the given object. If the given object is not a mapped and
	 * persisted entity, this method will throw an error. This call will ask the
	 * underlying persistence layer to remove the representation of this
	 * particular instance.
	 * 
	 * TODO: This is a modifying operation, so the implications of transaction
	 * handling have to be declared.
	 */
	void delete(Object o) throws ObjectMapperException;

	/**
	 * Asks the session to write all pending changes to the persistence layer.
	 */
	void commit();
}
