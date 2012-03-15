package com.omf.om.api.session.proxy;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
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
	 * @param persistenceDelegate
	 *            The {@link PersistenceDelegate} the created proxy will use to
	 *            access the persistence backend.
	 * 
	 * @return A proxy object for the given type.
	 */
	Object create(Session session, EntityMapping entityMapping, PersistenceDelegate persistenceDelegate);

}
