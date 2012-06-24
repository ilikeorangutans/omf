package org.om.core.api.mapping.extractor;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.ItemMap;
import org.om.core.api.mapping.PropertyMapping;

/**
 * Extracts {@link PropertyMapping}s from a given type.
 * 
 * @author Jakob Külzer
 * 
 */
public interface ItemMappingExtractor {

	/**
	 * Extracts all {@link PropertyMapping}s from the given type.
	 * 
	 * @param type
	 * @return
	 * @throws MappingException
	 */
	ItemMap extract(Class<?> type) throws MappingException;

}
