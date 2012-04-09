package org.om.core.impl.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.om.core.api.annotation.Entity;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.PropertyMapping;

/**
 * @author tome
 */
public class EntityUtils {
	/**
	 * get the value of the @Id field of an @Entity
	 */
	public static Object getEntityId(EntityMapping entityMapping, Object object) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException,
			IllegalAccessException {
		/*
		 * the id field
		 */
		String fieldName = entityMapping.getIdProperty().getFieldname();
		/*
		 * get the getter
		 */
		Method getter = getGetter(object, fieldName);
		/*
		 * return the value
		 */
		return getter.invoke(object, (Object[]) null);
	}

	private static Method getGetter(Object object, String fieldName) throws NoSuchMethodException {
		if ((null != fieldName) && (fieldName.length() > 0)) {
			/*
			 * make the name
			 */
			char[] b = fieldName.toCharArray();
			b[0] = Character.toUpperCase(b[0]);
			String getterName = new String("get") + new String(b);
			/*
			 * get the method
			 */
			return object.getClass().getMethod(getterName);
		} else {
			return null;
		}
	}

	public static Object getEntityPropertyValue(PropertyMapping propertyMapping, Object object) throws NoSuchFieldException, InvocationTargetException,
			NoSuchMethodException, IllegalAccessException {
		/*
		 * the field name
		 */
		String fieldName = propertyMapping.getFieldname();
		/*
		 * get the getter
		 */
		Method getter = getGetter(object, fieldName);
		/*
		 * return the value
		 */
		return getter.invoke(object, (Object[]) null);
	}

	/**
	 * Returns true if the given class object is annotated with @{@link Entity}.
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isEntity(Class<?> clazz) {
		return clazz.getAnnotation(Entity.class) != null;
	}
}
