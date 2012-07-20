package org.om.core.impl.mapping;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Fields;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.Mapping;

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

	public MappedField getIdProperty() {
		return fields.getIdProperty();
	}

	public Fields getMappedFields() {
		return fields;
	}

	public Mapping getMappingByField(String fieldname) {
		return fields.getField(fieldname).getMapping();
	}

	public String getName() {
		return name;
	}

	public Class<?> getTypeClass() {
		return type;
	}

	public boolean hasField(String field) {
		return fields.hasField(field);
	}

	public boolean hasProperty(String property) {
		return false;
	}

	public void setFields(Fields propertyMappings) {
		this.fields = propertyMappings;
	}

	@Override
	public String toString() {
		return "EntityMappingImpl [fields=" + fields + ", name=" + name + ", type=" + type + "]";
	}

	public void validate() throws MappingException {
		if (getIdProperty() == null)
			throw new MappingException("Entity " + type.getName() + " is missing id property. Annotate it with @Property and @Id.");
	}
}
