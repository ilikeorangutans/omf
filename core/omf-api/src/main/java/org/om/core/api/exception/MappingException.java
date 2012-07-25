package org.om.core.api.exception;

public class MappingException extends ObjectMapperException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MappingException(Class<?> type, String string) {
		this(String.format("Type %s is not an entity: %s", type.getName(), string));
	}

	public MappingException(String message) {
		super(message);
	}

	public MappingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MappingException(Throwable cause) {
		super(cause);
	}

}
