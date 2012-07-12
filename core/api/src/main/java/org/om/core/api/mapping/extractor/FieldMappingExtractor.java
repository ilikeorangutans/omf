package org.om.core.api.mapping.extractor;

import java.lang.reflect.Field;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.PropertyMapping;

/**
 * Extracts {@link PropertyMapping}s from a given type.
 * 
 * @author Jakob Külzer
 * 
 */
public interface FieldMappingExtractor {

	/**
	 * Extracts a {@link MappedField} from the given field.
	 * 
	 * @param type
	 * @return
	 * @throws MappingException
	 */
	MappedField extract(Field field) throws MappingException;

}
