package org.om.core.impl.persistence.jcr.util;

import java.math.*;
import java.util.*;

import javax.jcr.*;

/**
 * Quick'n'dirty class to map {@link PropertyType}.* to {@link Class}es.
 *
 * @author Jakob Külzer
 */
public class PropertyTypeToClass {
	private static final Class<?>[] TYPES;
	static {
		TYPES = new Class<?>[13];
		TYPES[PropertyType.STRING] = String.class;
		TYPES[PropertyType.BINARY] = byte[].class;
		TYPES[PropertyType.LONG] = long.class;
		TYPES[PropertyType.DOUBLE] = double.class;
		TYPES[PropertyType.DATE] = Calendar.class;
		TYPES[PropertyType.BOOLEAN] = boolean.class;
		TYPES[PropertyType.NAME] = String.class;
		TYPES[PropertyType.DECIMAL] = BigDecimal.class;
	}

	/**
	 * Maps a {@link PropertyType}.* type to a {@link Class} object.
	 * 
	 * @param type
	 * @return
	 */
	public static Class<?> getClassForType(int type) {
		if ((type >= TYPES.length) || (type < 0)) {
			throw new IllegalArgumentException("Don't know how to map type " + type + " to a class.");
		}
		return TYPES[type];
	}

	/**
	 * Maps a {@link Class} object to a JCR type.
	 * 
	 * @return
	 */
	public static int getTypeForClass(Class<?> type) {
		if (type == String.class) {
			return PropertyType.STRING;
		}
		if ((type == int.class) || (type == Integer.class) || (type == long.class) || (type == Long.class)) {
			return PropertyType.LONG;
		}
		if ((type == float.class) || (type == Float.class) || (type == double.class) || (type == Double.class)) {
			return PropertyType.DOUBLE;
		}
		if ((type == boolean.class) || (type == Boolean.class)) {
			return PropertyType.LONG;
		}
		if (type == byte[].class) {
			return PropertyType.BINARY;
		}
		if (type == Calendar.class) {
			return PropertyType.DATE;
		}
		if (type == BigDecimal.class) {
			return PropertyType.DECIMAL;
		}
		throw new IllegalArgumentException("Don't know how to map class " + type.getName() + " to a JCR property type.");
	}
}
