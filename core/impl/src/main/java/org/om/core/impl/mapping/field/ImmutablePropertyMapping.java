package org.om.core.impl.mapping.field;

import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.impl.util.ClassUtils;

public class ImmutablePropertyMapping implements PropertyMapping {

	private final String propertyName;
	private final boolean simpleType;
	private final String defaultValue;

	public ImmutablePropertyMapping(String propertyName, Class<?> propertyType, String defaultValue) {

		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
		simpleType = String.class.equals(propertyType) || ClassUtils.isPrimitiveOrAutoboxed(propertyType);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public boolean isId() {
		return false;
	}

	public boolean isPrimitiveOrWrappedType() {
		return simpleType;
	}

	@Override
	public String toString() {
		return "ImmutablePropertyMapping [propertyName=" + propertyName + ", simpleType=" + simpleType + ", defaultValue=" + defaultValue + "]";
	}

}
