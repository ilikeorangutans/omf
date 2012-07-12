package org.om.core.impl.mapping;

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

	public ImmutableCollectionMapping(Class<?> fieldType, Class<?> targetType, String location) {
		this.targetType = targetType;
		this.location = location;
	}

	public String getLocation() {
		return location;
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
		return "ImmutableCollectionMapping [location=" + location + ", targetType=" + targetType + "]";
	}

}
