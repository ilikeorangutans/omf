package org.om.core.impl.persistence.delegate;

import java.util.*;

import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.*;

/**
 * @author Jakob Külzer
 * @author tom
 */
public class TestingPersistenceContext implements PersistenceContext {
	private static final Map<String, Object> properties = new HashMap<String, Object>();

	public TestingPersistenceContext addProperty(String propertyName, Object value) {
		properties.put(propertyName, value);
		return this;
	}

	public Object getProperty(PropertyMapping propertyMapping) {
		return properties.get(propertyMapping.getPropertyName());
	}

	public Object getProperty(String path) {
		return properties.get(path);
	}

	public boolean hasProperty(String propertyName) {
		return properties.containsKey(propertyName);
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) {
		properties.put(propertyMapping.getPropertyName(), object);
	}
}
