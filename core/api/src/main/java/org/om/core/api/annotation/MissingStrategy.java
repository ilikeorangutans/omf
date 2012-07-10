package org.om.core.api.annotation;

/**
 * Describes strategies for handling non existing properties.
 * 
 * @author Jakob Külzer
 * 
 */
public enum MissingStrategy {

	/**
	 * Return <tt>null</tt> if the property is not found. This applies both for
	 * primitive and reference types. This is the default behavior.
	 */
	ReturnNull,

	/**
	 * Return a default value. The default value can be specified with the
	 * {@link Property} annotation.
	 */
	DefaultValue,

	/**
	 * Throw an exception. The type of the exception can be specified with the
	 * {@link Property} annotation.
	 */
	ThrowException

}
