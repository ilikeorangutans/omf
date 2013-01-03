/*
 * Copyright 2012 Jakob Külzer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.om.core.impl.mapping.extractor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.CollectionMode;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.MapKeyStrategy;
import org.om.core.api.annotation.Mapped;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.annotation.NULL;
import org.om.core.api.annotation.Property;
import org.om.core.api.exception.MappingException;
import org.om.core.api.exception.MissingException;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.extractor.FieldMappingExtractor;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.impl.mapping.ImmutableCollectionMapping;
import org.om.core.impl.mapping.ImmutableMappedField;
import org.om.core.impl.mapping.field.ImmutableIdMapping;
import org.om.core.impl.mapping.field.ImmutablePropertyMapping;
import org.om.core.impl.mapping.field.ImmutableReferenceMapping;
import org.om.core.impl.util.ClassUtils;
import org.om.core.impl.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldMappingExtractorImpl implements FieldMappingExtractor {

	private static final Logger LOGGER = LoggerFactory.getLogger(FieldMappingExtractorImpl.class);

	private void checkMapping(String fieldname, boolean isProperty, boolean isCollection, boolean isId) {
		if (isCollection && isProperty && isId) {
			throw new MappingException("Field " + fieldname + " is mapped as @Id, @Property and @Collection. Make up your mind!");
		}

		if (isProperty && isId) {
			throw new MappingException("Cannot map field " + fieldname + " @Id and @Property. Properties cannot be @Id fields!");
		}
		if (isCollection && isId) {
			throw new MappingException("Cannot map field " + fieldname + " @Id and @Collection. Collections cannot be @Id fields!");
		}
	}

	public MappedField extract(Field field) {

		final Mapped mapped = field.getAnnotation(Mapped.class);
		final Id id = field.getAnnotation(Id.class);
		final Collection collection = field.getAnnotation(Collection.class);
		final Property property = field.getAnnotation(Property.class);

		final boolean isMappedField = mapped != null || id != null || collection != null || property != null;

		if (!isMappedField) {
			LOGGER.trace("Field {} is not annotated, ignoring in mapping.", field.getName());
			return null;
		}

		LOGGER.trace("Found annotated field {}", field.getName());

		final Mapping mapping = extractMapping(field);

		final Class<?> fieldType = field.getType();
		final Class<?> implementationType = mapping.getImplementationType();

		final boolean validImplementationType = String.class.equals(implementationType) || EntityUtils.isEntity(implementationType)
				|| ClassUtils.isPrimitiveOrAutoboxed(implementationType);
		if (!validImplementationType)
			throw new MappingException("Target type " + fieldType + " is not an entity.");

		final MappedField mappedField = new ImmutableMappedField(field.getName(), fieldType, mapping, getMissingStrategy(mapped), getMissingException(mapped));
		return mappedField;
	}

	private Mapping extractCollectionMapping(Mapped mapped, Collection collection, Field field) {
		final Class<?> declaredType = collection.targetType();

		Class<?> fieldType = field.getType();
		final boolean isList = List.class.equals(fieldType);
		final boolean isSet = Set.class.equals(fieldType);
		final boolean isMap = Map.class.equals(fieldType);
		final boolean isSupportedType = isList || isSet || isMap;
		if (!isSupportedType)
			throw new MappingException("Collection field " + field.getName() + " is of type " + fieldType
					+ " but only java.util.List, java.util.Map or java.util.Set are supported.");

		final String location;
		if (collection.location() == null || collection.location().isEmpty()) {
			location = field.getName();
		} else {
			location = collection.location();
		}

		final CollectionMode collectionMapping = collection.mode();
		final MapKeyStrategy keyMapStrategy = collection.keyStrategy();
		final Class<?> implementationType = getImplementationType(mapped, declaredType);

		return new ImmutableCollectionMapping(fieldType, declaredType, implementationType, location, collectionMapping, keyMapStrategy);
	}

	private Mapping extractIdMapping(Id id, Field field) {
		return ImmutableIdMapping.INSTANCE;
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
	public Mapping extractMapping(Field field) {
		if (field == null)
			throw new IllegalArgumentException("Cannot extract property mapping from field, it's null.");
		final String fieldname = field.getName();

		LOGGER.trace("Extracting mapping for field {}", fieldname);

		final Mapped mapped = field.getAnnotation(Mapped.class);
		final Property annotation = field.getAnnotation(Property.class);
		final Collection collection = field.getAnnotation(Collection.class);
		final Id id = field.getAnnotation(Id.class);

		final boolean isProperty = annotation != null;
		final boolean isCollection = collection != null;
		final boolean isId = id != null;

		checkMapping(fieldname, isProperty, isCollection, isId);

		final Mapping mapping;
		if (isId) {
			mapping = extractIdMapping(id, field);
		} else if (isProperty) {
			mapping = extractPropertyMapping(mapped, annotation, field);
		} else {
			mapping = extractCollectionMapping(mapped, collection, field);
		}

		return mapping;

	}

	Mapping extractPropertyMapping(Mapped mapped, Property annotation, Field field) {
		final String fieldname = field.getName();
		final Class<?> declaredType = field.getType();
		final boolean primitiveOrAutoboxed = ClassUtils.isPrimitiveOrAutoboxed(declaredType) || String.class.equals(declaredType);

		final String propertyName;
		final boolean hasNameSetOnAnnotation = annotation.name() != null && annotation.name().length() > 1;
		if (hasNameSetOnAnnotation) {
			propertyName = annotation.name();
		} else {
			propertyName = fieldname;
		}

		if (primitiveOrAutoboxed) {
			return new ImmutablePropertyMapping(propertyName, declaredType, annotation.defaultValue());
		} else {
			Class<?> implementationType = getImplementationType(mapped, declaredType);
			return new ImmutableReferenceMapping(declaredType, implementationType, propertyName, annotation.lookupMode());
		}
	}

	/**
	 * Returns the implementation type to be used for the given {@link Mapped}
	 * annotation and declared type. If the given annotation is null or the
	 * implementation type returned by it is null, this will return the declared
	 * type, otherwise the specified implementation type.
	 * 
	 * @param mapped
	 * @param declaredType
	 * @return
	 */
	private Class<?> getImplementationType(Mapped mapped, Class<?> declaredType) {
		return mapped == null || mapped.implementationType() == NULL.class ? declaredType : mapped.implementationType();
	}

	private Class<? extends RuntimeException> getMissingException(Mapped mapped) {
		return mapped == null ? MissingException.class : mapped.missingException();
	}

	private MissingStrategy getMissingStrategy(Mapped mapped) {
		return mapped == null ? MissingStrategy.ReturnNull : mapped.missingStrategy();
	}
}
