package org.om.core.api.session;

/**
 * A single persistence session. A session acts as factory for entities.
 * 
 * Sessions are not threadsafe, they are meant to be used by a single thread
 * only.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Session {

	/**
	 * @param clazz
	 * @param path
	 * @return
	 */
	<T> T get(Class<T> clazz, Object id);

	/**
	 * Closes the session.
	 * 
	 * TODO: What does this actually mean? Will the session not be able to
	 * produce further objects? Will it close the underlying persistence layer?
	 * We need to define the semantics.
	 */
	void close();
}
