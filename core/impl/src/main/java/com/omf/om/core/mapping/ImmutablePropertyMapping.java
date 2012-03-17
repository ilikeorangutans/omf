package com.omf.om.core.mapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.omf.om.api.annotation.PropertyMissingStrategy;
import com.omf.om.api.annotation.PropertyNameStrategy;
import com.omf.om.api.mapping.PropertyMapping;

public class ImmutablePropertyMapping implements PropertyMapping {

	private static final Set<Class<?>> AUTOBOXING_TYPES = new HashSet(Arrays.asList(Boolean.class, Character.class, Byte.class, Short.class, Integer.class,
			Long.class, Float.class, Double.class));

	private final String defaultValue;
	private final String fieldname;
	private final Class<? extends Exception> missingException;
	private final PropertyMissingStrategy missingStrategy;
	private final PropertyNameStrategy nameStrategy;
	private final String propertyName;
	private final Class<?> propertyType;
	private final boolean isId;
	private final boolean simpleType;

	public ImmutablePropertyMapping(String fieldname, boolean isId, PropertyNameStrategy nameStrategy, String propertyName, Class<?> propertyType,
			String defaultValue, PropertyMissingStrategy missingStrategy, Class<? extends Exception> missingException) {
		this.fieldname = fieldname;
		this.isId = isId;
		this.nameStrategy = nameStrategy;
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.defaultValue = defaultValue;
		this.missingStrategy = missingStrategy;
		this.missingException = missingException;
		simpleType = propertyType.isPrimitive() || String.class.equals(propertyType) || AUTOBOXING_TYPES.contains(propertyType);
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
		if (fieldname == null) {
			if (other.fieldname != null)
				return false;
		} else if (!fieldname.equals(other.fieldname))
			return false;
		if (missingException == null) {
			if (other.missingException != null)
				return false;
		} else if (!missingException.equals(other.missingException))
			return false;
		if (missingStrategy != other.missingStrategy)
			return false;
		if (nameStrategy != other.nameStrategy)
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		return true;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getFieldname() {
		return fieldname;
	}

	public Class<RuntimeException> getMissingException() {
		return (Class<RuntimeException>) missingException;
	}

	public PropertyMissingStrategy getMissingStrategy() {
		return missingStrategy;
	}

	public PropertyNameStrategy getNameStrategy() {
		return nameStrategy;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Class<?> getPropertyType() {
		return propertyType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + ((fieldname == null) ? 0 : fieldname.hashCode());
		result = prime * result + ((missingException == null) ? 0 : missingException.hashCode());
		result = prime * result + ((missingStrategy == null) ? 0 : missingStrategy.hashCode());
		result = prime * result + ((nameStrategy == null) ? 0 : nameStrategy.hashCode());
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
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
		return "ImmutablePropertyMapping [fieldname=" + fieldname + ", isId=" + isId + ", propertyName=" + propertyName + ", propertyType=" + propertyType
				+ ", defaultValue=" + defaultValue + ", missingStrategy=" + missingStrategy + ", missingException=" + missingException + ", nameStrategy="
				+ nameStrategy + "]";
	}
}
