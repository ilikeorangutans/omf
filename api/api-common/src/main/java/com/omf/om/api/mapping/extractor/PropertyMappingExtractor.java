package com.omf.om.api.mapping.extractor;

import java.util.Set;

import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.PropertyMapping;

public interface PropertyMappingExtractor {

	/**
	 * Extracts all {@link PropertyMapping}s from the given type.
	 * 
	 * @param type
	 * @return
	 * @throws MappingException
	 */
	Set<PropertyMapping> extract(Class<?> type) throws MappingException;

}
