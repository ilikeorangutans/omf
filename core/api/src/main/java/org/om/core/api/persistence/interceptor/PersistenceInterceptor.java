package org.om.core.api.persistence.interceptor;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.persistence.PersistenceDelegate;

/**
 * Intercepts calls to fields in a proxy and retrieves the appropriate data from
 * a {@link PersistenceDelegate}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PersistenceInterceptor {

	Object getProperty(Mapping mapping);

}
