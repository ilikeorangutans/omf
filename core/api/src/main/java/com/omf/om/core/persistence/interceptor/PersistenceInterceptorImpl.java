package com.omf.om.core.persistence.interceptor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.PersistenceInterceptor;

public class PersistenceInterceptorImpl implements PersistenceInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceInterceptorImpl.class);

	private final PersistenceDelegate delegate;

	public PersistenceInterceptorImpl(PersistenceDelegate delegate) {
		this.delegate = delegate;
	}

	public Object getProperty(PropertyMapping propertyMapping) {

		if (propertyMapping == null)
			throw new NullPointerException("propertyMapping is null");

		if (!delegate.hasProperty(propertyMapping)) {
			return handleMisingProperty(propertyMapping);
		}

		// TODO: This should be refactored into individual classes.
		if (propertyMapping.isPrimitiveOrWrappedType()) {
			LOGGER.trace("Property {} is primitive or boxing type", propertyMapping.getPropertyName());
			return handlePrimitiveType(propertyMapping, delegate.getProperty(propertyMapping));
		} else {
			throw new RuntimeException("Not implemented yet.");
		}

	}

	/**
	 * Handles type conversion for primitive or boxed primitive types.
	 * 
	 * @param propertyMapping
	 * @param input
	 * @return
	 */
	private Object handlePrimitiveType(PropertyMapping propertyMapping, Object input) {
		if (input == null)
			return null;

		// If the returned object already has the correct type, just return it.
		if (propertyMapping.getPropertyType() == input.getClass()) {
			LOGGER.trace("Object from delegate already has correct type {}", input.getClass());
			return input;
		}

		// If the returned object is a String, we'll have to parse it.
		if (String.class.equals(input.getClass())) {
			LOGGER.trace("Delegate returned String for {}, attempting to parse...", input);

			// This is real ugly: PropertyEditors return boxed primitive
			// types instead of exactly what you ask for.
			final PropertyEditor editor = PropertyEditorManager.findEditor(propertyMapping.getPropertyType());
			editor.setAsText((String) input);
			return editor.getValue();
		}

		assert false : "We have an unhandled primitive type " + propertyMapping.getPropertyType() + ".";
		return null;
	}

	private Object handleMisingProperty(PropertyMapping propertyMapping) {

		LOGGER.debug("Delegate cannot provide property {}, going to {}", propertyMapping.getPropertyName(), propertyMapping.getMissingStrategy());

		switch (propertyMapping.getMissingStrategy()) {
		case DefaultValue:

			// If we're handling a primitive type we'll need to handle type
			// conversion:

			if (propertyMapping.isPrimitiveOrWrappedType()) {
				return handlePrimitiveType(propertyMapping, propertyMapping.getDefaultValue());
			}
			return propertyMapping.getDefaultValue();

		case ReturnNull:
			return null;

		case ThrowException:
			final Class<RuntimeException> exceptionType = propertyMapping.getMissingException();

			try {
				throw exceptionType.getConstructor(String.class).newInstance("Could not retrieve property " + propertyMapping.getPropertyName());
			} catch (SecurityException e) {
				throw new RuntimeException("Could not construct exception for missing property.", e);
			} catch (NoSuchMethodException e) {

				throw new RuntimeException("Implement me! ");

			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Could not construct exception for missing property.", e);
			} catch (InstantiationException e) {
				throw new RuntimeException("Could not construct exception for missing property.", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Could not construct exception for missing property.", e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException("Could not construct exception for missing property.", e);
			}

		}

		return null;
	}
}
