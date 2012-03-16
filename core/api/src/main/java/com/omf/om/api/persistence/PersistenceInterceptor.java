package com.omf.om.api.persistence;

import com.omf.om.api.mapping.PropertyMapping;

/**
 * Intercepts calls to fields in a proxy and retrieves the appropriate data from
 * a {@link PersistenceDelegate}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PersistenceInterceptor {

	Object getProperty(PropertyMapping propertyMapping);

}
