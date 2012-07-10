package org.om.core.impl.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Several utility functions for handling class objects and method names.
 * 
 * @author Jakob Külzer
 * 
 */
public class ClassUtils {

	private static final Pattern PATTERN = Pattern.compile("(get|is)([A-Z])([a-zA-Z0-9_]*)");

	@SuppressWarnings("unchecked")
	private static final Set<Class<?>> AUTOBOXING_TYPES = new HashSet<Class<?>>(Arrays.asList(Boolean.class, Character.class, Byte.class, Short.class,
			Integer.class, Long.class, Float.class, Double.class));

	/**
	 * 
	 * @param type
	 * @return Returns true if the given type is either a primitive Java type or
	 *         one of the wrapper types.
	 */
	public static final boolean isPrimitiveOrAutoboxed(Class<?> type) {
		return type.isPrimitive() || AUTOBOXING_TYPES.contains(type);
	}

	/**
	 * Returns the field name for a Java Bean style getter method name. For
	 * example, for <tt>getFooBar</tt> this method will return <tt>fooBar</tt>.
	 * 
	 * @param name
	 * @return
	 */
	public static String extractFieldName(String name) {
		Matcher matcher = PATTERN.matcher(name);
		matcher.find();
		final String fieldName = matcher.group(2).toLowerCase() + matcher.group(3);
		return fieldName;
	}

	/**
	 * Returns true if the given String is a Java Bean style getter.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isGetter(String name) {
		return PATTERN.matcher(name).find();
	}

}
