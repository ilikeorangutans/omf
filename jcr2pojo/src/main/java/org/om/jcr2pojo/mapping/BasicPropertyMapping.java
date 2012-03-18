package org.om.jcr2pojo.mapping;

import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.annotation.PropertyNameStrategy;
import org.om.core.api.mapping.PropertyMapping;

/**
 * 
 * @author tome
 * 
 */
public class BasicPropertyMapping implements PropertyMapping {

	private final String fieldname;
	private final String propertyName;
	private final Class<?> propertyType;

	/**
	 * ctor
	 */
	public BasicPropertyMapping(String fieldname, String propertyName, Class<?> propertyType) {
		this.fieldname = fieldname;
		this.propertyName = propertyName;
		this.propertyType = propertyType;
	}

	public String getDefaultValue() {
		return null;
	}

	public String getFieldname() {

		return fieldname;
	}

	public Class<RuntimeException> getMissingException() {
		return null;
	}

	public PropertyMissingStrategy getMissingStrategy() {
		return null;
	}

	public PropertyNameStrategy getNameStrategy() {
		return null;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Class<?> getPropertyType() {
		return this.propertyType;
	}

	public boolean isId() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPrimitiveOrWrappedType() {
		// TODO Auto-generated method stub
		return false;
	}

}
