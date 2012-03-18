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

	private String fieldname;
	private String propertyName;

	public BasicPropertyMapping(String fieldname, String propertyName) {
		this.fieldname = fieldname;
		this.propertyName = propertyName;
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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isId() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPrimitiveOrWrappedType() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}
