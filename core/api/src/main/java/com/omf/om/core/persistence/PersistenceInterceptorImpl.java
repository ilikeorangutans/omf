package com.omf.om.core.persistence;

import java.lang.reflect.InvocationTargetException;

import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.PersistenceInterceptor;

public class PersistenceInterceptorImpl implements PersistenceInterceptor {

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

		return null;
	}

	private Object handleMisingProperty(PropertyMapping propertyMapping) {

		switch (propertyMapping.getMissingStrategy()) {
		case DefaultValue:
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
