package org.om.jcr2pojo.mapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.om.core.api.mapping.PropertyMap;
import org.om.core.api.mapping.PropertyMapping;

/**
 * 
 * @author tome
 * 
 */
public class BasicPropertyMap implements PropertyMap {

	/**
	 * properties
	 */
	private final Map<String, PropertyMapping> properties = new HashMap<String, PropertyMapping>();

	public void add(String name, PropertyMapping propertyMapping) {
		properties.put(name, propertyMapping);
	}

	public Collection<PropertyMapping> getAll() {
		return properties.values();
	}

	public PropertyMapping getField(String fieldname) {

		return null;
	}

	public PropertyMapping getIdProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	public PropertyMapping getProperty(String name) {
		return properties.get(name);
	}

	public int getSize() {
		return properties.size();
	}

	public boolean hasField(String name) {

		return false;
	}

	public boolean hasProperty(String name) {
		return properties.containsKey(name);
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

}
