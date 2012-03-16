package com.omf.om.api.persistence.interceptor.factory;

import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.interceptor.PersistenceInterceptor;

public interface PersistenceInterceptorFactory {

	PersistenceInterceptor create(PersistenceDelegate delegate);

}
