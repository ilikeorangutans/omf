package org.om.core.impl.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {

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

}
