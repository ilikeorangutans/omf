package com.omf.om.core.mapping.extractor;

import java.util.Set;

import com.omf.om.api.annotation.Entity;
import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.mapping.extractor.EntityMappingExtractor;
import com.omf.om.core.mapping.EntityMappingImpl;

public class EntityMappingExtractorImpl implements EntityMappingExtractor {

	public EntityMapping extract(Class<?> type) throws MappingException {
		if (type == null)
			throw new NullPointerException("Cannot extract entity mapping, type is null!");

		final Entity annotation = type.getAnnotation(Entity.class);
		if (annotation == null)
			throw new MappingException(type, "not annotated with @Entity");

		final EntityMappingImpl result = new EntityMappingImpl(type);

		final Set<PropertyMapping> propertyMappings = new PropertyMappingExtractorImpl().extract(type);
		result.setPropertyMappings(propertyMappings);

		return result;
	}
}
