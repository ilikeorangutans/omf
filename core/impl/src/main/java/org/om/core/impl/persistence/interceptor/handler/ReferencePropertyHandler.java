package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.handler.PropertyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles properties whose values are references to other entities.
 * 
 * @author Jakob Külzer
 * 
 */
public class ReferencePropertyHandler implements PropertyHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferencePropertyHandler.class);

	public Object retrieve(PropertyMapping propertyMapping, Object input) {
		return null;
	}

}
