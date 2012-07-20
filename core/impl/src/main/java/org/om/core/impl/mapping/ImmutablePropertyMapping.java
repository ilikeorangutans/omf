package org.om.core.impl.mapping;

import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.impl.util.ClassUtils;

public class ImmutablePropertyMapping extends AbstractMapping implements PropertyMapping {

	private final String defaultValue;
	private final boolean isId;
	private final String propertyName;
	private final boolean simpleType;

	public ImmutablePropertyMapping(String fieldname, boolean isId, String propertyName, Class<?> propertyType, String defaultValue,
			PropertyMissingStrategy missingStrategy, Class<? extends RuntimeException> missingException) {
		super(fieldname, propertyType, missingStrategy, missingException);

		this.isId = isId;
		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
		simpleType = String.class.equals(propertyType) || ClassUtils.isPrimitiveOrAutoboxed(propertyType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImmutablePropertyMapping other = (ImmutablePropertyMapping) obj;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
		if (isId != other.isId)
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		if (simpleType != other.simpleType)
			return false;
		return true;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + (isId ? 1231 : 1237);
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		result = prime * result + (simpleType ? 1231 : 1237);
		return result;
	}

	public boolean isId() {
		return isId;
	}

	public boolean isPrimitiveOrWrappedType() {
		return simpleType;
	}

	@Override
	public String toString() {
		return "ImmutablePropertyMapping [propertyName=" + propertyName + ", defaultValue=" + defaultValue + ", getFieldname()=" + getFieldname()
				+ ", getFieldType()=" + getFieldType() + "]";
	}

}
