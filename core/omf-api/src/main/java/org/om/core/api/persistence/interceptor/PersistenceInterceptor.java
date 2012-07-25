package org.om.core.api.persistence.interceptor;

import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;

/**
 * Intercepts calls to fields in a proxy and retrieves the appropriate data from
 * a {@link PersistenceAdapter}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PersistenceInterceptor {

	Object get(MappedField mappedField);

}
