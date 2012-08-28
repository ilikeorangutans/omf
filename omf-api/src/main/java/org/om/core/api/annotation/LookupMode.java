package org.om.core.api.annotation;

/**
 * Describes how object mapper will look up a property. This mostly applies to
 * reference fields. The idea is that in order to retrieve a property, object
 * mapper needs to know where to get the appropriate property/node. There's
 * basically several possible approaches, and while object mapper will do it's
 * best to try to guess what you're trying to do, there's situations where
 * you'll have to clarify your intent.
 * 
 * There's two options on how the mapper will resolve fields, either by using
 * the named configured in the configuration directly or by using the local
 * field as a reference to where to locate the field. Usually you'll want
 * {@link #Reference} as your default unless you know exactly where your
 * reference will be living. In that case, use {@link #Direct}.
 * 
 * @author Jakob Külzer
 */
public enum LookupMode {

	/**
	 * Use the name of the property as the fixed location and disregard any
	 * local values.
	 */
	Direct,

	/**
	 * The persistence adapter will look for a property with the configured name
	 * of the property and use it's value as the id to retrieve the referenced
	 * field.
	 */
	Reference

}
