package com.omf.om.core.mapping.extractor;

import com.omf.om.api.annotation.Entity;
import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMap;
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

		final PropertyMap propertyMap = new PropertyMappingExtractorImpl().extract(type);
		result.setPropertyMap(propertyMap);

		if (result.getIdProperty() == null) {
			throw new MappingException("Type " + type.getName() + " does not have an ID property. Annotate one property with @Id.");
		}

		return result;
	}
}
