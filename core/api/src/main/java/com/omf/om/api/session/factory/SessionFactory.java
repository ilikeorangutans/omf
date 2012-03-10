package com.omf.om.api.session.factory;

import com.omf.om.api.session.Session;

/**
 * A session factory creates instances of {@link Session}.
 * 
 * @author Jakob Külzer
 * 
 * @param <T>
 */
public interface SessionFactory<T> {

	Session getSession(T persistenceContext);

}
