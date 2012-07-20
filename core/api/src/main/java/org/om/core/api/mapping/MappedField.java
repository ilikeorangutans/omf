package org.om.core.api.mapping;

import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.mapping.field.Mapping;

/**
 * Describes the field on a mapped entity.
 * 
 * @author Jakob Külzer
 * 
 */
public interface MappedField {

	/**
	 * Return the actual mapping.
	 * 
	 * @return
	 */
	Mapping getMapping();

	/**
	 * Exception to throw when {@link #getMissingStrategy()} is set to
	 * {@link MissingStrategy#ThrowException} and the property cannot be found.
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
	MissingStrategy getMissingStrategy();

	/**
	 * Returns the name of the field.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Returns the type of the field.
	 * 
	 * @return
	 */
	Class<?> getType();

}
