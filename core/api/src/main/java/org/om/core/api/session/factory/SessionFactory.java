package org.om.core.api.session.factory;

import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.session.Session;

/**
 * A session factory creates instances of {@link Session}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface SessionFactory {

	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	Session getSession(PersistenceContext persistenceContext);

}
