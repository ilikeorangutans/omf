package org.om.core.api.mapping.field;

/**
 * Describes the mapping of a property.
 * 
 * @author Jakob Külzer
 * 
 */
public interface PropertyMapping extends Mapping {

	/**
	 * Returns the default value according to the annotation.
	 * 
	 * @return
	 */
	String getDefaultValue();

	/**
	 * Name of the property in the underlying persistence layer.
	 * 
	 * @return
	 */
	String getPropertyName();

}
