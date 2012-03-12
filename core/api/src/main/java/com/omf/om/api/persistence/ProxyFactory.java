package com.omf.om.api.persistence;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.session.Session;

/**
 * Creates proxy instances for entities.
 * 
 * @author Jakob Külzer
 * 
 */
public interface ProxyFactory {

	/**
	 * Create a proxy object for the given {@link Session} and
	 * {@link EntityMapping}. The returned object will have the same type or
	 * supertype as described by the {@link EntityMapping}. The returned proxy
	 * object will be bound to the given session.
	 * 
	 * @param session
	 * @param entityMapping
	 * @return
	 */
	Object create(Session session, PersistenceContext persistenceContext, EntityMapping entityMapping);

}
