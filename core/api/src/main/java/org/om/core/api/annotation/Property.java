package org.om.core.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
	 * {@link MissingStrategy#DefaultValue}. The returned value must be
	 * translatable into the type of the field, otherwise a conversion error
	 * will occur.
	 * 
	 * @return
	 */
	String defaultValue() default DEFAULT_VALUE;

}
