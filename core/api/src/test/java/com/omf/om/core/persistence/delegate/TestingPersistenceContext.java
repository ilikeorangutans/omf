package com.omf.om.core.persistence.delegate;

import java.util.HashMap;
import java.util.Map;

import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.persistence.PersistenceContext;

public class TestingPersistenceContext implements PersistenceContext {

	private final Map<String, Object> properties = new HashMap<String, Object>();

	public Object getProperty(PropertyMapping propertyMapping) {
		return properties.get(propertyMapping.getPropertyName());
	}

	public TestingPersistenceContext addProperty(String propertyName, Object value) {
		properties.put(propertyName, value);
		return this;
	}

}
