package org.om.core.impl.util;

import java.lang.reflect.*;

import org.om.core.api.annotation.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;

/**
 * @author tome
 */
public class EntityUtils {
	/**
	 * get the value of the @Id field of an @Entity
	 */
	@Deprecated
	public static Object getEntityId(EntityMapping entityMapping, Object object) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		entityMapping.getIdProperty().getName();
		/*
		 * get
		 */
		// return BeanUtils.getProperty(object, fieldName);
		return null;
	}

	/**
	 * get an entity property value.
	 */
	@Deprecated
	public static Object getEntityPropertyValue(PropertyMapping propertyMapping, Object object) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		// /*
		// * the field name
		// */
		// String fieldName = propertyMapping.getPropertyName();
		// /*
		// * get
		// */
		// String propValue = BeanUtils.getProperty(object, fieldName);
		// /*
		// * return converted value
		// */
		// return ConvertUtils.convert(propValue,
		// propertyMapping.getFieldType());
		return null;
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
	public static void setEntityId(EntityMapping entityMapping, Object object, Object value) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		entityMapping.getIdProperty().getName();
	}

	/**
	 * set entity property value.
	 */
	public static void setEntityPropertyValue(PropertyMapping propertyMapping, Object object, Object value)
			throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		// /*
		// * the field name
		// */
		// String fieldName = propertyMapping.getFieldname();
		// /*
		// * set
		// */
		// BeanUtils.setProperty(object, fieldName, value);
	}
}
