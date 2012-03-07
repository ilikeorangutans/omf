package com.omf.om.core.mapping;

import java.util.Collections;
import java.util.Set;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMapping;

public class EntityMappingImpl implements EntityMapping {

	private final Class<?> type;
	private Set<PropertyMapping> propertyMappings;

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

}
