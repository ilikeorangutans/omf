package org.om.core.impl.mapping;

import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.mapping.Mapping;

public abstract class AbstractMapping implements Mapping {

	private final String fieldname;
	private final Class<? extends RuntimeException> missingException;
	private final PropertyMissingStrategy missingStrategy;
	private final Class<?> fieldType;

	public AbstractMapping(String fieldname, Class<?> fieldType, PropertyMissingStrategy missingStrategy, Class<? extends RuntimeException> missingException) {
		super();
		this.fieldname = fieldname;
		this.missingException = missingException;
		this.missingStrategy = missingStrategy;
		this.fieldType = fieldType;
	}

	public String getFieldname() {
		return fieldname;
	}

	@SuppressWarnings("unchecked")
	public Class<RuntimeException> getMissingException() {
		return (Class<RuntimeException>) missingException;
	}

	public PropertyMissingStrategy getMissingStrategy() {
		return missingStrategy;
	}

	public Class<?> getFieldType() {
		return fieldType;
	}

}