package org.om.core.impl.mapping;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.ItemMap;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;

/**
 * Immutable implementation of {@link ItemMap}.
 * 
 * @author Jakob Külzer
 * 
 */
public class ImmutablePropertyMap implements ItemMap {

	private final Map<String, PropertyMapping> properties;
	private final Map<String, Mapping> fields;
	private PropertyMapping id;

	public ImmutablePropertyMap(Set<Mapping> mappings) {
		final Map<String, PropertyMapping> tmpProps = new HashMap<String, PropertyMapping>();
		final Map<String, Mapping> tmpFields = new HashMap<String, Mapping>();
		for (Mapping mapping : mappings) {

			if (mapping.isId()) {
				if (id != null)
					throw new MappingException("Found more than one @Id property!");

				id = (PropertyMapping) mapping;
			}

			assert !tmpFields.containsKey(mapping.getFieldname()) : "How is this possible? Fieldname appeared twice in mapping.";

			if (mapping instanceof PropertyMapping) {
				final PropertyMapping propertyMapping = (PropertyMapping) mapping;

				if (tmpProps.containsKey(propertyMapping.getPropertyName()))
					throw new MappingException("Property " + propertyMapping.getPropertyName() + " is mapped twice.");

				tmpProps.put(propertyMapping.getPropertyName(), propertyMapping);
			}

			if (mapping instanceof CollectionMapping) {
				final CollectionMapping collectionMapping = (CollectionMapping) mapping;

			}
			tmpFields.put(mapping.getFieldname(), mapping);
		}

		this.properties = Collections.unmodifiableMap(tmpProps);
		this.fields = Collections.unmodifiableMap(tmpFields);
	}

	public Collection<Mapping> getAll() {
		return fields.values();
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

	public Mapping getField(String fieldname) {
		return fields.get(fieldname);
	}

	public int getSize() {
		return fields.size();
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

}
