package org.om.core.impl.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.om.core.api.annotation.Entity;
import org.om.core.api.mapping.EntityMapping;

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
		String fieldName = entityMapping.getIdProperty().getName();
		/*
		 * get
		 */
		return BeanUtils.getProperty(object, fieldName);
	}

	/**
	 * get an entity property value.
	 */
	public static Object getEntityPropertyValue(PropertyMapping propertyMapping, Object object) throws NoSuchFieldException, InvocationTargetException,
			NoSuchMethodException, IllegalAccessException {
		/*
		 * the field name
		 */
		String fieldName = propertyMapping.getFieldname();
		/*
		 * get
		 */
		String propValue = BeanUtils.getProperty(object, fieldName);
		/*
		 * return converted value
		 */
		return ConvertUtils.convert(propValue, propertyMapping.getFieldType());
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

	/**
	 * set the value of the @Id field of an @Entity
	 */
	public static void setEntityId(EntityMapping entityMapping, Object object, Object value) throws NoSuchFieldException, InvocationTargetException,
			NoSuchMethodException, IllegalAccessException {
		/*
		 * the id field
		 */
		String fieldName = entityMapping.getIdProperty().getFieldname();
		/*
		 * set
		 */
		BeanUtils.setProperty(object, fieldName, value);
	}

	/**
	 * set entity property value.
	 */
	public static void setEntityPropertyValue(PropertyMapping propertyMapping, Object object, Object value) throws NoSuchFieldException,
			InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		/*
		 * the field name
		 */
		String fieldName = propertyMapping.getFieldname();
		/*
		 * set
		 */
		BeanUtils.setProperty(object, fieldName, value);
	}
}
