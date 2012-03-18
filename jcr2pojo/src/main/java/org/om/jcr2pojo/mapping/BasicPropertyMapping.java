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

	public BasicPropertyMapping(String fieldname) {
		this.fieldname = fieldname;
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
		return null;
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
}
