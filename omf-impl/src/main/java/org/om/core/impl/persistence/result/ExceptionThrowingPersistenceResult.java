package org.om.core.impl.persistence.result;

import java.lang.reflect.*;

import org.om.core.api.persistence.result.*;

/**
 * Result that encapsulates the actual missing strategy from the mapped field settings.
 *
 * @author Jakob Külzer
 */
@Deprecated
public class ExceptionThrowingPersistenceResult implements PersistenceResult {
	private final Class<? extends RuntimeException> exceptionType;

	ExceptionThrowingPersistenceResult(Class<? extends RuntimeException> exceptionClass) {
		exceptionType = exceptionClass;
	}

	@Override
	public Object getValue() {
		try {
			throw exceptionType.getConstructor(String.class).newInstance("Could not retrieve property ");
		} catch (final SecurityException e) {
			throw new RuntimeException("Could not construct exception for missing property.", e);
		} catch (final NoSuchMethodException e) {
			throw new RuntimeException("Implement me! ");
		} catch (final IllegalArgumentException e) {
			throw new RuntimeException("Could not construct exception for missing property.", e);
		} catch (final InstantiationException e) {
			throw new RuntimeException("Could not construct exception for missing property.", e);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException("Could not construct exception for missing property.", e);
		} catch (final InvocationTargetException e) {
			throw new RuntimeException("Could not construct exception for missing property.", e);
		}
	}

	@Override
	public boolean hasResult() {
		return false;
	}
}
