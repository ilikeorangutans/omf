package org.om.core.impl.persistence.interceptor;

import java.lang.reflect.InvocationTargetException;

import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decorator that provides default behavior for different
 * {@link PropertyMissingStrategy}s.
 * 
 * @author Jakob Külzer
 * 
 */
final class MissingBehaviorPersistenceDelegateDecorator implements PersistenceDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(MissingBehaviorPersistenceDelegateDecorator.class);

	private final PersistenceDelegate delegate;

	public MissingBehaviorPersistenceDelegateDecorator(PersistenceDelegate delegate) {
		this.delegate = delegate;
		LOGGER.trace("New decorator around {}", delegate);
	}

	public boolean canProvide(Mapping mapping) {
		return delegate.canProvide(mapping);
	}

	public Object getProperty(Mapping mapping) {

		final boolean canProvide = delegate.canProvide(mapping);
		if (canProvide) {
			LOGGER.debug("Delegate can provide {}", mapping.getFieldname());
			return delegate.getProperty(mapping);
		}

		LOGGER.trace("Delegate cannot provide {}", mapping.getFieldname());

		if (mapping.getMissingStrategy() == PropertyMissingStrategy.ReturnNull)
			return null;

		// TODO: the following is kinda yuck. The setting DefaultValue works
		// only for property mappings.
		if (mapping.getMissingStrategy() == PropertyMissingStrategy.DefaultValue) {
			if (mapping instanceof PropertyMapping) {
				PropertyMapping pm = (PropertyMapping) mapping;
				return pm.getDefaultValue();
			}
		}

		throwPropertyMissingException(mapping);
		return null; // This is quite silly, but the java compiler doesn't seem
						// that the previous method call never returns.
	}

	private void throwPropertyMissingException(Mapping mapping) {

		final Class<RuntimeException> exceptionType = mapping.getMissingException();

		try {
			throw exceptionType.getConstructor(String.class).newInstance("Could not retrieve property " + mapping.getFieldname());
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