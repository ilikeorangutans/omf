package com.omf.om.core.mapping;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.PropertyMap;
import com.omf.om.api.mapping.PropertyMapping;

/**
 * Immutable implementation of {@link PropertyMap}.
 * 
 * @author Jakob Külzer
 * 
 */
public class ImmutablePropertyMap implements PropertyMap {

	private final Map<String, PropertyMapping> properties;
	private final Map<String, PropertyMapping> fields;
	private PropertyMapping id;

	public ImmutablePropertyMap(Set<PropertyMapping> properties) {
		final Map<String, PropertyMapping> tmpProps = new HashMap<String, PropertyMapping>();
		final Map<String, PropertyMapping> tmpFields = new HashMap<String, PropertyMapping>();
		for (PropertyMapping pm : properties) {

			if (pm.isId()) {
				if (id != null)
					throw new MappingException("Found more than one @Id property!");

				id = pm;
			}

			if (tmpProps.containsKey(pm.getPropertyName()))
				throw new MappingException("Property " + pm.getPropertyName() + " is mapped twice.");

			assert !tmpFields.containsKey(pm.getFieldname()) : "How is this possible? Fieldname appeared twice in mapping.";

			tmpProps.put(pm.getPropertyName(), pm);
			tmpFields.put(pm.getFieldname(), pm);
		}

		this.properties = Collections.unmodifiableMap(tmpProps);
		this.fields = Collections.unmodifiableMap(tmpFields);
	}

	public Collection<PropertyMapping> getAll() {
		return properties.values();
	}

	public boolean hasProperty(String name) {
		return properties.containsKey(name);
	}

	public boolean hasField(String name) {
		return fields.containsKey(name);
	}

	public PropertyMapping getIdProperty() {
		return id;
	}

	public PropertyMapping getProperty(String name) {
		return properties.get(name);
	}

	public PropertyMapping getField(String fieldname) {
		return fields.get(fieldname);
	}

	public int getSize() {
		return properties.size();
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

}
