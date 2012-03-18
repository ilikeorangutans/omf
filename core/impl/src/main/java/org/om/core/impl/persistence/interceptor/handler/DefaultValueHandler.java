package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.handler.PropertyHandler;

public class DefaultValueHandler implements PropertyHandler {

	private final String defaultValue;

	public DefaultValueHandler(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Object retrieve(PropertyMapping propertyMapping, Object input) {
		// TODO Auto-generated method stub
		return null;
	}

}
