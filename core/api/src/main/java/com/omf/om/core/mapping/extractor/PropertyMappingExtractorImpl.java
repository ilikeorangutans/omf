package com.omf.om.core.mapping.extractor;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.omf.om.api.annotation.Entity;
import com.omf.om.api.annotation.Id;
import com.omf.om.api.annotation.Property;
import com.omf.om.api.annotation.PropertyNameStrategy;
import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.mapping.extractor.PropertyMappingExtractor;
import com.omf.om.core.mapping.ImmutablePropertyMapping;

public class PropertyMappingExtractorImpl implements PropertyMappingExtractor {

	public Set<PropertyMapping> extract(Class<?> type) {
		if (type == null)
			throw new NullPointerException("Cannot extract entity mapping, type is null!");

		final Entity entityAnnotation = type.getAnnotation(Entity.class);
		if (entityAnnotation == null)
			throw new MappingException(type, "not annotated with @Entity, cannot extract property mapping");

		final Set<PropertyMapping> mappings = new HashSet<PropertyMapping>();

		for (Field field : type.getDeclaredFields()) {

			final Property annotation = field.getAnnotation(Property.class);
			if (annotation == null)
				continue;

			final PropertyMapping propertyMapping = fromField(field);
			mappings.add(propertyMapping);

		}

		return mappings;
	}

	/**
	 * Extracts a {@link PropertyMapping} from the given field.
	 * 
	 * TODO: this should go into a factory supporting different types of
	 * property mappings.
	 * 
	 * @param field
	 * @return the extracted property mapping.
	 * @throws MappingException
	 *             if the mapping cannot be constructed or if there's no
	 *             mapping.
	 */
	public PropertyMapping fromField(Field field) {
		if (field == null)
			throw new NullPointerException("Cannot extract property mapping from field, it's null.");

		final Property annotation = field.getAnnotation(Property.class);
		if (annotation == null)
			throw new MappingException("Cannot extract mapping from field " + field.getName() + ", no annotation!");

		final PropertyNameStrategy nameStrategy = annotation.namingStrategy();
		final String propertyName;
		final boolean hasNameSetOnAnnotation = annotation.name() != null && annotation.name().length() > 1;
		if (nameStrategy == PropertyNameStrategy.ProvidedName || hasNameSetOnAnnotation) {
			propertyName = annotation.name();
		} else {
			propertyName = field.getName();
		}

		final Id idAnnotation = field.getAnnotation(Id.class);
		final boolean isId = idAnnotation != null;

		return new ImmutablePropertyMapping(field.getName(), isId, nameStrategy, propertyName, field.getType(), annotation.defaultValue(),
				annotation.missingStrategy(), annotation.missingException());
	}
}
