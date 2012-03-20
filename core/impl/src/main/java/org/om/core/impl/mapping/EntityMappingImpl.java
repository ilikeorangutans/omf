package org.om.core.impl.mapping;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.PropertyMap;
import org.om.core.api.mapping.PropertyMapping;

public class EntityMappingImpl implements EntityMapping {

	private final Class<?> type;
	private PropertyMap propertyMappings;
	private String name;

	public String getName() {
		return name;
	}

	public EntityMappingImpl(Class<?> type) {
		this.type = type;
		this.name = null;
	}

	/**
	 * ctor for use by JCR2POJO
	 */
	public EntityMappingImpl(String name) {
		this.type = null;
		this.name = name;
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
