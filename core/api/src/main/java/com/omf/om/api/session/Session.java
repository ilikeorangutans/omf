package com.omf.om.api.session;

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
	<T> T get(Class<T> clazz, String path);

}
