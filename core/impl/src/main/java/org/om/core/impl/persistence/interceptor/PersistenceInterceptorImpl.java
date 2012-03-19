package org.om.core.impl.persistence.interceptor;

import java.lang.reflect.InvocationTargetException;

import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.handler.PropertyHandler;
import org.om.core.api.persistence.interceptor.handler.PropertyHandlerFactory;
import org.om.core.api.session.Session;

public class PersistenceInterceptorImpl implements PersistenceInterceptor {

	private final PersistenceDelegate delegate;

	private final PropertyHandlerFactory handlerFactory;

	private final Session session;

	public PersistenceInterceptorImpl(Session session, PropertyHandlerFactory handlerFactory, PersistenceDelegate delegate) {
		this.session = session;
		this.handlerFactory = handlerFactory;
		this.delegate = delegate;
	}

	public Object getProperty(PropertyMapping propertyMapping) {

		if (propertyMapping == null)
			throw new NullPointerException("propertyMapping is null");

		final PropertyHandler handler = handlerFactory.get(session, propertyMapping);
		final boolean propertyMissing = !delegate.hasProperty(propertyMapping);

		if (propertyMissing && propertyMapping.getMissingStrategy() == PropertyMissingStrategy.ThrowException) {
			throwPropertyMissingException(propertyMapping);
		}

		final String defaultValue = propertyMapping.getMissingStrategy() == PropertyMissingStrategy.ReturnNull ? null : propertyMapping.getDefaultValue();
		final Object value = propertyMissing ? defaultValue : delegate.getProperty(propertyMapping);

		assert handler != null : "No handler for property mapping " + propertyMapping + " found.";

		return handler.retrieve(propertyMapping, value);
	}

	private void throwPropertyMissingException(PropertyMapping propertyMapping) {

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

}
