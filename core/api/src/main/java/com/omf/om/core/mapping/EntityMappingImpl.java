package com.omf.om.core.mapping;

import java.util.Collections;
import java.util.Set;

import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMapping;

public class EntityMappingImpl implements EntityMapping {

	private final Class<?> type;
	private Set<PropertyMapping> propertyMappings;
	private PropertyMapping idPropertyMapping;

	public EntityMappingImpl(Class<?> type) {
		this.type = type;
	}

	public Class<?> getTypeClass() {
		return type;
	}

	public Set<PropertyMapping> getPropertyMappings() {
		return propertyMappings;
	}

	public void setPropertyMappings(Set<? extends PropertyMapping> propertyMappings) {
		this.propertyMappings = Collections.unmodifiableSet(propertyMappings);
	}

	public boolean hasField(String field) {
		for (PropertyMapping pm : propertyMappings) {
			if (pm.getFieldname().equals(field)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasProperty(String property) {
		return false;
	}

	public PropertyMapping getPropertyByField(String fieldname) {
		for (PropertyMapping pm : propertyMappings) {
			if (pm.getFieldname().equals(fieldname)) {
				return pm;
			}
		}

		throw new MappingException("Requested field " + fieldname + " is not mapped in entity " + this);
	}

	public PropertyMapping getIdProperty() {
		if (idPropertyMapping == null) {
			for (PropertyMapping pm : propertyMappings) {
				if (pm.isId()) {
					idPropertyMapping = pm;
					break;
				}
			}
		}

		return idPropertyMapping;
	}

	public void validate() throws MappingException {
		if (getIdProperty() == null)
			throw new MappingException("Entity " + type.getName() + " is missing id property. Annotate it with @Property and @Id.");
	}
}
