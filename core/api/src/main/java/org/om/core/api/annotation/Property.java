package org.om.core.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.om.core.api.exception.PropertyMissingException;

/**
 * Annotation that describes a property on an {@link Entity}.
 * 
 * @author Jakob Külzer
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {

	static final String DEFAULT_VALUE = "";

	/**
	 * Name of the property. If not set, will use the POJO field name.
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * Default value to use if {@link #missingStrategy()} is set to
	 * {@link PropertyMissingStrategy#DefaultValue}. The returned value must be
	 * translatable into the type of the field, otherwise a conversion error
	 * will occur.
	 * 
	 * @return
	 */
	String defaultValue() default DEFAULT_VALUE;

	/**
	 * Defines how object mapper will react to missing properties. The default
	 * is to return <tt>null</tt> if the referenced property cannot be found.
	 * 
	 * @see PropertyMissingStrategy
	 * 
	 * @return
	 */
	PropertyMissingStrategy missingStrategy() default PropertyMissingStrategy.ReturnNull;

	/**
	 * Exception to be thrown when the mapped property cannot be retrieved from
	 * the underlying persistence layer and {@link #missingStrategy()} is set to
	 * {@link PropertyMissingStrategy#ThrowException}.
	 * 
	 * The exception must have either a no-arg constructor or take one String
	 * parameter.
	 * 
	 * @return
	 */
	Class<? extends RuntimeException> missingException() default PropertyMissingException.class;

}
