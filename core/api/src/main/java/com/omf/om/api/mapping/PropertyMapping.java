package com.omf.om.api.mapping;

import com.omf.om.api.annotation.PropertyMissingStrategy;
import com.omf.om.api.annotation.PropertyNameStrategy;

/**
 * Describes the mapping of a property.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PropertyMapping {

	/**
	 * Returns the default value according to the annotation.
	 * 
	 * @return
	 */
	String getDefaultValue();

	/**
	 * Name of the field on the POJO.
	 * 
	 * @return
	 */
	String getFieldname();

	/**
	 * Exception to throw when {@link #getMissingStrategy()} is set to
	 * {@link PropertyMissingStrategy#ThrowException} and the property cannot be
	 * found.
	 * 
	 * @return
	 */
	Class<Exception> getMissingException();

	/**
	 * Strategy to be used when the given property cannot be found in the
	 * persistence layer.
	 * 
	 * @return
	 */
	PropertyMissingStrategy getMissingStrategy();

	/**
	 * Strategy to determine the name of the property in the underlying
	 * persistence layer.
	 * 
	 * @return
	 */
	PropertyNameStrategy getNameStrategy();

	/**
	 * Name of the property in the underlying persistence layer.
	 * 
	 * @return
	 */
	String getPropertyName();

}
