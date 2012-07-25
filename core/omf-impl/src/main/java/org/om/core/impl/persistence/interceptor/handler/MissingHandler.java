package org.om.core.impl.persistence.interceptor.handler;

import java.lang.reflect.InvocationTargetException;

import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;

public class MissingHandler implements ItemHandler {

	public static final MissingHandler INSTANCE = new MissingHandler();

	private MissingHandler() {
	}

	@Override
	public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {

		final MissingStrategy strategy = mappedField.getMissingStrategy();

		if (strategy == MissingStrategy.ReturnNull)
			return null;

		if (strategy == MissingStrategy.DefaultValue && mappedField.getMapping() instanceof PropertyMapping)
			return ((PropertyMapping) mappedField.getMapping()).getDefaultValue();

		try {
			throw mappedField.getMissingException().getConstructor(String.class).newInstance("Could not retrieve property ");
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
