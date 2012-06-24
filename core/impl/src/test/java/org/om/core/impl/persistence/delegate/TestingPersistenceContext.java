package org.om.core.impl.persistence.delegate;

import java.util.HashMap;
import java.util.Map;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceContext;

/**
 * @author Jakob Külzer
 * @author tom
 */
public class TestingPersistenceContext implements PersistenceContext {

	private static final Map<String, Object> properties = new HashMap<String, Object>();

	public Object getProperty(Mapping propertyMapping) {
		return properties.get(propertyMapping.getFieldname());
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) {
		properties.put(propertyMapping.getPropertyName(), object);
	}

	public TestingPersistenceContext addProperty(String propertyName, Object value) {
		properties.put(propertyName, value);
		return this;
	}

	public boolean hasProperty(String propertyName) {
		return properties.containsKey(propertyName);
	}

}
