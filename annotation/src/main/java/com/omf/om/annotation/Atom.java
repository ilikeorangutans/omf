package com.omf.om.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Jakob Külzer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Atom {

	/**
	 * Name of the atom that backs this field. Default is an empty String, which
	 * will cause the mapper to use the field name.
	 */
	String name() default "";

	/**
	 * Name of the container to retrieve the atom from. Defaults to an empty
	 * String, which will make the mapper to use the current container. The
	 * exact semantics of which container that is depends on the type of entity.
	 * 
	 * @return
	 */
	String container() default "";

}
