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

import org.om.core.api.annotation.*;
import org.om.core.api.mapping.field.*;

/**
 * Describes the mapping of a collection to a persistence backend.
 *
 * @author Jakob Külzer
 */
public interface CollectionMapping extends Mapping {
	/**
	 * Returns an instance of {@link CollectionMode} describing how the collection should be
	 * constructed.
	 * 
	 * @return
	 */
	CollectionMode getCollectionMode();

	/**
	 * Returns the type of the collection.
	 * 
	 * @return
	 */
	Class<?> getCollectionType();

	/**
	 * Returns the location that backs this collection.
	 * 
	 * @return
	 */
	String getLocation();

	/**
	 * Returns the configured {@link MapKeyStrategy}.
	 * 
	 * @return
	 */
	MapKeyStrategy getMapKeyStrategy();
}
