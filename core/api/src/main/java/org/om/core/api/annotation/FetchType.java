package org.om.core.api.annotation;

/**
 * Defines how a {@link Property} or {@link Collection} should be retrieved.
 * 
 * @author Jakob Külzer
 * 
 */
public @interface FetchType {

	public enum Strategy {
		Eager, Lazy
	}

	public Strategy value() default Strategy.Eager;

}
