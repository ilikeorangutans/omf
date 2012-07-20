package org.om.core.impl.mapping;

import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.mapping.CollectionMapping;

/**
 * Describes a mapping for collections.
 * 
 * @author Jakob Külzer
 * 
 */
public class ImmutableCollectionMapping extends AbstractMapping implements CollectionMapping {

	private final String location;
	private final Class<?> targetType;

	public ImmutableCollectionMapping(String fieldname, Class<?> fieldType, Class<?> targetType, String location, PropertyMissingStrategy missingStrategy,
			Class<? extends RuntimeException> missingException) {
		super(fieldname, fieldType, missingStrategy, missingException);
		this.targetType = targetType;
		this.location = location;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImmutableCollectionMapping other = (ImmutableCollectionMapping) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (targetType == null) {
			if (other.targetType != null)
				return false;
		} else if (!targetType.equals(other.targetType))
			return false;
		return true;
	}

	public String getLocation() {
		return location;
	}

	public Class<?> getTargetType() {
		return targetType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((targetType == null) ? 0 : targetType.hashCode());
		return result;
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

}
