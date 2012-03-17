package org.om.core.api.persistence.interceptor.factory;

import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;

public interface PersistenceInterceptorFactory {

	PersistenceInterceptor create(PersistenceDelegate delegate);

}
