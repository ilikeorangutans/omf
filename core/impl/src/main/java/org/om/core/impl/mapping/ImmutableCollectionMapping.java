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

	public ImmutableCollectionMapping(String fieldname, Class<?> fieldType, String location, PropertyMissingStrategy missingStrategy,
			Class<? extends RuntimeException> missingException) {
		super(fieldname, fieldType, missingStrategy, missingException);
		this.location = location;
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

	public String getLocation() {
		return location;
	}

}
