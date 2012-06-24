package org.om.core.api.persistence.interceptor;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.persistence.PersistenceAdapter;

/**
 * Intercepts calls to fields in a proxy and retrieves the appropriate data from
 * a {@link PersistenceAdapter}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PersistenceInterceptor {

	Object getProperty(Mapping mapping);

}
