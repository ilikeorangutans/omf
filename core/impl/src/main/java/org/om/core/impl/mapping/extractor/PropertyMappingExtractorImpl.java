package org.om.core.impl.mapping.extractor;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;
import org.om.core.api.annotation.PropertyNameStrategy;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.PropertyMap;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.mapping.extractor.PropertyMappingExtractor;
import org.om.core.impl.mapping.ImmutableCollectionMapping;
import org.om.core.impl.mapping.ImmutablePropertyMap;
import org.om.core.impl.mapping.ImmutablePropertyMapping;
import org.om.core.impl.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyMappingExtractorImpl implements PropertyMappingExtractor {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyMappingExtractorImpl.class);

	public PropertyMap extract(Class<?> type) {
		if (type == null)
			throw new NullPointerException("Cannot extract entity mapping, type is null!");

		final Entity entityAnnotation = type.getAnnotation(Entity.class);
		if (entityAnnotation == null)
			throw new MappingException(type, "not annotated with @Entity, cannot extract property mapping");

		final Set<PropertyMapping> mappings = new HashSet<PropertyMapping>();

		for (Field field : type.getDeclaredFields()) {

			final Property annotation = field.getAnnotation(Property.class);
			if (annotation == null) {
				LOGGER.debug("Field {} is not annotated, ignoring in mapping.", field.getName());
				continue;
			}

			LOGGER.debug("Found annotated field {}", field.getName());

			final PropertyMapping propertyMapping = fromField(field);
			mappings.add(propertyMapping);

		}

		return new ImmutablePropertyMap(mappings);
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
		final String fieldname = field.getName();
		if (annotation == null)
			throw new MappingException("Cannot extract mapping from field " + fieldname + ", no annotation!");

		final PropertyNameStrategy nameStrategy = annotation.namingStrategy();
		final String propertyName;
		final boolean hasNameSetOnAnnotation = annotation.name() != null && annotation.name().length() > 1;
		if (nameStrategy == PropertyNameStrategy.ProvidedName || hasNameSetOnAnnotation) {
			propertyName = annotation.name();
		} else {
			propertyName = fieldname;
		}

		final Id idAnnotation = field.getAnnotation(Id.class);
		final boolean isId = idAnnotation != null;
		final Class<?> type = field.getType();

		final boolean primitiveOrAutoboxed = ClassUtils.isPrimitiveOrAutoboxed(type) || String.class.equals(type);
		final boolean collectionType = Collection.class.isAssignableFrom(type);
		final boolean referenceType = !collectionType;
		if (primitiveOrAutoboxed || referenceType) {
			return new ImmutablePropertyMapping(fieldname, isId, nameStrategy, propertyName, type, annotation.defaultValue(), annotation.missingStrategy(),
					annotation.missingException());
		} else if (collectionType) {
			return new ImmutableCollectionMapping();
		} else {
			throw new MappingException("Don't know how to map field " + fieldname + " of type " + type);
		}

	}
}
