package org.om.core.api.persistence.interceptor.factory;

import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.session.Session;

/**
 * @author Jakob Külzer
 */
public interface PersistenceInterceptorFactory {

	PersistenceInterceptor create(Session session, PersistenceDelegate delegate);
}
