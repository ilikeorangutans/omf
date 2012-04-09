package org.om.core.impl.mapping.extractor;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.ItemMap;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.mapping.extractor.ItemMappingExtractor;
import org.om.core.impl.mapping.ImmutableCollectionMapping;
import org.om.core.impl.mapping.ImmutablePropertyMap;
import org.om.core.impl.mapping.ImmutablePropertyMapping;
import org.om.core.impl.util.ClassUtils;
import org.om.core.impl.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemMappingExtractorImpl implements ItemMappingExtractor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemMappingExtractorImpl.class);

	public ItemMap extract(Class<?> type) {
		if (type == null)
			throw new NullPointerException("Cannot extract entity mapping, type is null!");

		final Entity entityAnnotation = type.getAnnotation(Entity.class);
		if (entityAnnotation == null)
			throw new MappingException(type, "not annotated with @Entity, cannot extract property mapping");

		final Set<Mapping> mappings = new HashSet<Mapping>();

		for (Field field : type.getDeclaredFields()) {

			final Property annotation = field.getAnnotation(Property.class);
			if (annotation == null) {
				LOGGER.debug("Field {} is not annotated, ignoring in mapping.", field.getName());
				continue;
			}

			LOGGER.debug("Found annotated field {}", field.getName());

			final Mapping itemMapping = fromField(field);
			mappings.add(itemMapping);

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
	public Mapping fromField(Field field) {
		if (field == null)
			throw new NullPointerException("Cannot extract property mapping from field, it's null.");

		LOGGER.trace("Extracting mapping for field {}", field.getName());

		final Property annotation = field.getAnnotation(Property.class);
		final Collection collection = field.getAnnotation(Collection.class);
		final String fieldname = field.getName();
		if (annotation == null)
			throw new MappingException("Cannot extract mapping from field " + fieldname + ", no annotation!");

		final String propertyName;
		final boolean hasNameSetOnAnnotation = annotation.name() != null && annotation.name().length() > 1;
		if (hasNameSetOnAnnotation) {
			propertyName = annotation.name();
		} else {
			propertyName = fieldname;
		}

		final Id idAnnotation = field.getAnnotation(Id.class);
		final boolean isId = idAnnotation != null;
		final Class<?> type = field.getType();

		final boolean primitiveOrAutoboxed = ClassUtils.isPrimitiveOrAutoboxed(type) || String.class.equals(type);
		final boolean collectionType = collection != null;
		final boolean referenceType = !collectionType;
		if (primitiveOrAutoboxed || referenceType) {
			return new ImmutablePropertyMapping(fieldname, isId, propertyName, type, annotation.defaultValue(), annotation.missingStrategy(),
					annotation.missingException());
		} else if (collectionType) {
			final Class<?> targetType = collection.targetType();
			// TODO: this behaviour should be moved into a separate model
			// validator.
			final boolean validTargetType = String.class.equals(targetType) || EntityUtils.isEntity(targetType)
					|| ClassUtils.isPrimitiveOrAutoboxed(targetType);
			if (!validTargetType)
				throw new MappingException("Target type " + targetType.getName() + " is not an entity.");

			// TODO: Add location
			return new ImmutableCollectionMapping(fieldname, type, targetType, "", annotation.missingStrategy(), annotation.missingException());
		} else {
			throw new MappingException("Don't know how to map field " + fieldname + " of type " + type);
		}

	}
}
