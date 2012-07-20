package org.om.core.api.mapping;

import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.PropertyMissingStrategy;

public interface Mapping {

	/**
	 * Name of the field on the POJO.
	 * 
	 * @return
	 */
	String getFieldname();

	/**
	 * Returns the type of the field.
	 * 
	 * @return
	 */
	Class<?> getFieldType();

	/**
	 * Exception to throw when {@link #getMissingStrategy()} is set to
	 * {@link PropertyMissingStrategy#ThrowException} and the property cannot be
	 * found.
	 * 
	 * @return
	 */
	Class<RuntimeException> getMissingException();

	/**
	 * Strategy to be used when the given property cannot be found in the
	 * persistence layer.
	 * 
	 * @return
	 */
	PropertyMissingStrategy getMissingStrategy();

	/**
	 * Returns true if this mapping is an {@link Id} mapping.
	 * 
	 * @return
	 */
	boolean isId();

	/**
	 * Returns if the type of the property is either Java simple type (int,
	 * float, double, ...) or java.lang.String.
	 * 
	 * @return
	 */
	boolean isPrimitiveOrWrappedType();

}
