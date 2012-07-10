package org.om.core.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.om.core.api.exception.MissingException;

/**
 * Marks the given field as a mapped field.
 * 
 * @author Jakob Külzer
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mapped {

	/**
	 * Defines how object mapper will react to missing properties. The default
	 * is to return <tt>null</tt> if the referenced property cannot be found.
	 * 
	 * @see MissingStrategy
	 * 
	 * @return
	 */
	MissingStrategy missingStrategy() default MissingStrategy.ReturnNull;

	/**
	 * Exception to be thrown when the mapped property cannot be retrieved from
	 * the underlying persistence layer and {@link #missingStrategy()} is set to
	 * {@link MissingStrategy#ThrowException}.
	 * 
	 * The exception must have either a no-arg constructor or take one String
	 * parameter.
	 * 
	 * @return
	 */
	Class<? extends RuntimeException> missingException() default MissingException.class;

}
