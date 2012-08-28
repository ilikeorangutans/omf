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
package org.om.core.api.mapping;

import java.util.Collection;

import org.om.core.api.mapping.field.Mapping;

/**
 * Contains all {@link MappedField}s for an {@link EntityMapping}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Fields extends Iterable<MappedField> {

	/**
	 * Returns all {@link Mapping}s.
	 * 
	 * @return
	 */
	Collection<MappedField> getAll();

	/**
	 * Finds a field by name.
	 * 
	 * @param fieldname
	 * @return
	 */
	MappedField getField(String fieldname);

	MappedField getFieldByMapping(Mapping mapping);

	/**
	 * Returns the property that has been marked as the id property.
	 * 
	 * @return
	 */
	MappedField getIdProperty();

	int getSize();

	/**
	 * Returns true if this map contains a field with the given name.
	 * 
	 * @param name
	 * @return
	 */
	boolean hasField(String name);

	boolean isEmpty();
}
