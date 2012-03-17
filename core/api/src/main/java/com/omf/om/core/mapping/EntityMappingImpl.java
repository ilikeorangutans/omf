package com.omf.om.core.mapping;

import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMap;
import com.omf.om.api.mapping.PropertyMapping;

public class EntityMappingImpl implements EntityMapping {

	private final Class<?> type;
	private PropertyMap propertyMappings;

	public EntityMappingImpl(Class<?> type) {
		this.type = type;
	}

	public Class<?> getTypeClass() {
		return type;
	}

	public PropertyMap getPropertyMappings() {
		return propertyMappings;
	}

	public void setPropertyMap(PropertyMap propertyMappings) {
		this.propertyMappings = propertyMappings;
	}

	public boolean hasField(String field) {
		return propertyMappings.hasField(field);
	}

	public boolean hasProperty(String property) {
		return propertyMappings.hasProperty(property);
	}

	public PropertyMapping getPropertyByField(String fieldname) {
		return propertyMappings.getField(fieldname);
	}

	public PropertyMapping getIdProperty() {
		return propertyMappings.getIdProperty();
	}

	public void validate() throws MappingException {
		if (getIdProperty() == null)
			throw new MappingException("Entity " + type.getName() + " is missing id property. Annotate it with @Property and @Id.");
	}
}
