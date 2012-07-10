package org.om.core.impl.mapping.extractor;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.om.core.api.annotation.Entity;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Fields;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.core.impl.mapping.ImmutableFields;

public class EntityMappingExtractorImpl implements EntityMappingExtractor {

	public EntityMapping extract(Class<?> type) throws MappingException {
		if (type == null)
			throw new NullPointerException("Cannot extract entity mapping, type is null!");

		final Entity annotation = type.getAnnotation(Entity.class);
		if (annotation == null)
			throw new MappingException(type, "not annotated with @Entity");

		final EntityMappingImpl result = new EntityMappingImpl(type, type.getName());

		final Fields mappedFields = extractFields(type);
		result.setFields(mappedFields);

		if (result.getIdProperty() == null) {
			throw new MappingException("Type " + type.getName() + " does not have an ID property. Annotate one property with @Id.");
		}

		return result;
	}

	public Fields extractFields(Class<?> type) {
		if (type == null)
			throw new NullPointerException("Cannot extract entity mapping, type is null!");

		final Entity entityAnnotation = type.getAnnotation(Entity.class);
		if (entityAnnotation == null)
			throw new MappingException(type, "not annotated with @Entity, cannot extract property mapping");

		final Set<MappedField> fields = new HashSet<MappedField>();

		final FieldMappingExtractorImpl fieldMappingExtractor = new FieldMappingExtractorImpl();
		for (Field field : type.getDeclaredFields()) {
			MappedField mappedField = fieldMappingExtractor.extract(field);
			if (mappedField != null)
				fields.add(mappedField);
		}

		return new ImmutableFields(fields);

	}

}
