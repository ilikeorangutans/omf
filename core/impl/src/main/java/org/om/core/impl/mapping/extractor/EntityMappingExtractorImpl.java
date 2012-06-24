package org.om.core.impl.mapping.extractor;

import org.om.core.api.annotation.Entity;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.ItemMap;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.impl.mapping.EntityMappingImpl;

public class EntityMappingExtractorImpl implements EntityMappingExtractor {

	public EntityMapping extract(Class<?> type) throws MappingException {
		if (type == null)
			throw new NullPointerException("Cannot extract entity mapping, type is null!");

		final Entity annotation = type.getAnnotation(Entity.class);
		if (annotation == null)
			throw new MappingException(type, "not annotated with @Entity");

		final EntityMappingImpl result = new EntityMappingImpl(type);

		final ItemMap propertyMap = new ItemMappingExtractorImpl().extract(type);
		result.setPropertyMap(propertyMap);

		if (result.getIdProperty() == null) {
			throw new MappingException("Type " + type.getName() + " does not have an ID property. Annotate one property with @Id.");
		}

		return result;
	}
}
