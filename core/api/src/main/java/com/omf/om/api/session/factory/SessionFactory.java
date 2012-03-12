package com.omf.om.api.session.factory;

import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.session.Session;

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
