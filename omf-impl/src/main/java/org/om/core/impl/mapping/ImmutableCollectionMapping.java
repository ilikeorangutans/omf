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
package org.om.core.impl.mapping;

import org.om.core.api.annotation.CollectionMode;
import org.om.core.api.annotation.MapKeyStrategy;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.CollectionMapping;

/**
 * Describes a mapping for collections.
 * 
 * @author Jakob Külzer
 * 
 */
public class ImmutableCollectionMapping implements CollectionMapping {

	private final String location;
	private final Class<?> targetType;
	private final CollectionMode collectionMode;
	private final MapKeyStrategy mapKeyStrategy;
	private final Class<?> implementationType;

	public ImmutableCollectionMapping(Class<?> fieldType, Class<?> targetType, Class<?> implementationType, String location, CollectionMode collectionMode,
			MapKeyStrategy mapKeyStrategy) {

		if (!targetType.isAssignableFrom(implementationType))
			throw new MappingException("Collection mapping implementation type (" + implementationType + ") is not the same or a subtype of the target type ("
					+ targetType + ").");

		this.targetType = targetType;
		this.implementationType = implementationType;
		this.location = location;
		this.collectionMode = collectionMode;
		this.mapKeyStrategy = mapKeyStrategy;
	}

	public ImmutableCollectionMapping(Class<?> fieldType, Class<?> targetType, String location) {
		this(fieldType, targetType, targetType, location, CollectionMode.Children, MapKeyStrategy.Name);
	}

	@Override
	public CollectionMode getCollectionMode() {
		return collectionMode;
	}

	@Override
	public Class<?> getImplementationType() {
		return implementationType;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public MapKeyStrategy getMapKeyStrategy() {
		return mapKeyStrategy;
	}

	public Class<?> getTargetType() {
		return targetType;
	}

	/**
	 * Always returns false as a collection cannot serve as an identifier.
	 */
	public boolean isId() {
		return false;
	}

	/**
	 * Always returns false.
	 */
	public boolean isPrimitiveOrWrappedType() {
		return false;
	}

	@Override
	public String toString() {
		return "ImmutableCollectionMapping [location=" + location + ", targetType=" + targetType + ", collectionMode=" + collectionMode + ", mapKeyStrategy="
				+ mapKeyStrategy + "]";
	}

}
