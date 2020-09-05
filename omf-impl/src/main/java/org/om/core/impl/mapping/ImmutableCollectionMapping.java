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

import org.om.core.api.annotation.*;
import org.om.core.api.mapping.*;
import org.om.core.impl.mapping.field.*;

/**
 * Describes a mapping for collections.
 *
 * @author Jakob Külzer TODO: this should be split into a mapping for java.util.Collection and
 *         java.util.Map.
 */
public class ImmutableCollectionMapping extends AbstractImmutableMapping implements CollectionMapping {
	private final CollectionMode collectionMode;
	private final Class<?> collectionType;
	private final String location;
	private final MapKeyStrategy mapKeyStrategy;

	public ImmutableCollectionMapping(Class<?> collectionType, Class<?> declaredType, Class<?> implementationType, String location, CollectionMode collectionMode, MapKeyStrategy mapKeyStrategy) {
		super(declaredType, implementationType);
		this.collectionType = collectionType;
		this.location = location;
		this.collectionMode = collectionMode;
		this.mapKeyStrategy = mapKeyStrategy;
	}

	public ImmutableCollectionMapping(Class<?> collectionType, Class<?> declaredType, String location) {
		this(collectionType, declaredType, declaredType, location, CollectionMode.Children, MapKeyStrategy.Name);
	}

	@Override
	public CollectionMode getCollectionMode() {
		return collectionMode;
	}

	@Override
	public Class<?> getCollectionType() {
		return collectionType;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public MapKeyStrategy getMapKeyStrategy() {
		return mapKeyStrategy;
	}

	/**
	 * Always returns false as a collection cannot serve as an identifier.
	 */
	@Override
	public boolean isId() {
		return false;
	}

	/**
	 * Always returns false.
	 */
	@Override
	public boolean isPrimitiveOrWrappedType() {
		return false;
	}

	@Override
	public String toString() {
		return "ImmutableCollectionMapping [location=" + location + ", collectionMode=" + collectionMode + ", mapKeyStrategy=" + mapKeyStrategy + ", collectionType=" + collectionType
				+ ", getImplementationType()=" + getImplementationType() + ", getDeclaredType()=" + getDeclaredType() + "]";
	}
}
