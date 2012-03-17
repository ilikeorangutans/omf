/**
 * 
 */
package com.omf.om.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as an entity. Every class that should be mapped by the object
 * mapper needs to be annotated with this.
 * 
 * Each entity must have exactly one {@link Property} that acts as the
 * identifier property.
 * 
 * 
 * @author Jakob Külzer
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {

}
