package org.om.core.impl.mapping;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;

public class EntityMappingImpl implements EntityMapping {
	private Fields fields;
	private final String name;
	private final Class<?> type;

	public EntityMappingImpl(Class<?> type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public MappedField getByFieldName(String name) {
		return fields.getField(name);
	}

	@Override
	public MappedField getIdProperty() {
		return fields.getIdProperty();
	}

	@Override
	public Fields getMappedFields() {
		return fields;
	}

	@Override
	public Mapping getMappingByField(String fieldname) {
		return fields.getField(fieldname).getMapping();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<?> getTypeClass() {
		return type;
	}

	@Override
	public boolean hasField(String field) {
		return fields.hasField(field);
	}

	public boolean hasProperty(String property) {
		return false;
	}

	public void setFields(Fields propertyMappings) {
		fields = propertyMappings;
	}

	@Override
	public String toString() {
		return "EntityMappingImpl [fields=" + fields + ", name=" + name + ", type=" + type + "]";
	}

	@Override
	public void validate() throws MappingException {
		if (getIdProperty() == null) {
			throw new MappingException("Entity " + type.getName() + " is missing id property. Annotate it with @Property and @Id.");
		}
	}
}
