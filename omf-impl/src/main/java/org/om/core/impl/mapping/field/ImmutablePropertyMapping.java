package org.om.core.impl.mapping.field;

import org.om.core.api.mapping.field.PropertyMapping;

public class ImmutablePropertyMapping extends AbstractImmutableMapping implements PropertyMapping {

	private final String defaultValue;
	private final String propertyName;

	public ImmutablePropertyMapping(String propertyName, Class<?> propertyType, String defaultValue) {
		super(propertyType, propertyType);

		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
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

	@Override
	public String toString() {
		return "ImmutablePropertyMapping [defaultValue=" + defaultValue + ", propertyName=" + propertyName + ", getDeclaredType()=" + getDeclaredType()
				+ ", getImplementationType()=" + getImplementationType() + ", isPrimitiveOrWrappedType()=" + isPrimitiveOrWrappedType() + "]";
	}

}
