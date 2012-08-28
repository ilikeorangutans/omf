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
