package org.om.core.api.annotation;

import org.om.core.api.exception.PropertyMissingException;

/**
 * Maps a collection.
 * 
 * Please note that depending on the concrete collection implementation in use,
 * you'll want to make sure that your collection entries implement
 * {@link #hashCode()} and {@link #equals(Object)}.
 * 
 * @author Jakob Külzer
 * 
 */
public @interface Collection {

	public static final String LOCATION_RELATIVE_USING_FIELDNAME = "";

	/**
	 * Defines how object mapper should react if the underlying persistence
	 * backend cannot resolve the nodes pointed to by {@link #location()}. Note
	 * that {@link PropertyMissingStrategy#DefaultValue} will cause an empty
	 * collection to be returned. This is the default.
	 * 
	 * @return
	 */
	PropertyMissingStrategy missingStrategy() default PropertyMissingStrategy.DefaultValue;

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
	Class<? extends Exception> missingException() default PropertyMissingException.class;

	/**
	 * Describes the location of where to load the collection from. This has
	 * implications as of how the actual backend will load the data.
	 * 
	 * There are two possible ways of specifying a path to a collection. It can
	 * either be an absolute location, starting with a slash, or relative,
	 * without a beginning slash. If the location is relative, it will be
	 * resolved relative to the node of the containing {@link Entity}.
	 * 
	 * The default is to use the field name as a relative path.
	 */
	String location() default LOCATION_RELATIVE_USING_FIELDNAME;

	/**
	 * Declares the type of the collection entries. The class needs to be a
	 * valid {@link Entity}.
	 * 
	 * @return
	 */
	Class<?> targetType();

}
