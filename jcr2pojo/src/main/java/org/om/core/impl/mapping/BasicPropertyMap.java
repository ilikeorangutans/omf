package org.om.core.impl.mapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.om.core.api.mapping.ItemMap;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;

/**
 * 
 * @author tome
 * 
 */
public class BasicPropertyMap implements ItemMap {

	/**
	 * properties
	 */
	private final Map<String, Mapping> properties = new HashMap<String, Mapping>();
	private final Map<String, Mapping> fields = new HashMap<String, Mapping>();

	public void add(PropertyMapping propertyMapping) {
		fields.put(propertyMapping.getFieldname(), propertyMapping);
		properties.put(propertyMapping.getPropertyName(), propertyMapping);

	}

	public Collection<Mapping> getAll() {
		return properties.values();
	}

	public PropertyMapping getField(String fieldname) {
		return (PropertyMapping) fields.get(fieldname);
	}

	public PropertyMapping getIdProperty() {
		return null;
	}

	public PropertyMapping getProperty(String name) {
		return (PropertyMapping) properties.get(name);
	}

	public int getSize() {
		return properties.size();
	}

	public boolean hasField(String name) {
		return fields.containsKey(name);
	}

	public boolean hasProperty(String name) {
		return properties.containsKey(name);
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}
}
