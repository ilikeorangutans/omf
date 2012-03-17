package org.om.core.api.annotation;

/**
 * Describes strategies for handling non existing properties.
 * 
 * @author Jakob Külzer
 * 
 */
public enum PropertyMissingStrategy {

	ReturnNull, DefaultValue, ThrowException

}
