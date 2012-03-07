package com.omf.om.api.exception;

public class MappingException extends ObjectMapperException {

	public MappingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MappingException(String message) {
		super(message);
	}

	public MappingException(Throwable cause) {
		super(cause);
	}

	public MappingException(Class<?> type, String string) {
		this(String.format("Type %s is not an entity: %s", type.getName(), string));
	}

}
