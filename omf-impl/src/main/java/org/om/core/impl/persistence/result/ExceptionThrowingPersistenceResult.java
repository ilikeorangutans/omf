package org.om.core.impl.persistence.result;

import java.lang.reflect.InvocationTargetException;

import org.om.core.api.persistence.result.PersistenceResult;

/**
 * Result that encapsulates the actual missing strategy from the mapped field
 * settings.
 * 
 * @author Jakob Külzer
 * 
 */
@Deprecated
public class ExceptionThrowingPersistenceResult implements PersistenceResult {

	private Class<? extends RuntimeException> exceptionType;

	ExceptionThrowingPersistenceResult(Class<? extends RuntimeException> exceptionClass) {
		this.exceptionType = exceptionClass;
	}

	@Override
	public Object getValue() {

		try {
			throw exceptionType.getConstructor(String.class).newInstance("Could not retrieve property ");
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

	@Override
	public boolean hasResult() {
		return false;
	}

}
