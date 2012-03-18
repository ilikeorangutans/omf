package org.om.core.impl.persistence.jcr.util;

import java.util.Calendar;

import javax.jcr.PropertyType;

/**
 * Quick'n'dirty class to map {@link PropertyType}.* to {@link Class}es.
 * 
 * @author Jakob Külzer
 * 
 */
public class PropertyTypeToClass {

	private static final Class<?>[] TYPES;
	static {
		TYPES = new Class<?>[6];
		TYPES[PropertyType.STRING] = String.class;
		TYPES[PropertyType.BINARY] = byte[].class;
		TYPES[PropertyType.LONG] = long.class;
		TYPES[PropertyType.DOUBLE] = double.class;
		TYPES[PropertyType.DATE] = Calendar.class;
		TYPES[PropertyType.BOOLEAN] = boolean.class;
	}

	public static Class<?> getClassForType(int type) {
		if (type >= TYPES.length || type < 0)
			throw new IllegalArgumentException("Don't know how to map type " + type + " to a class.");

		return TYPES[type];
	}
}
