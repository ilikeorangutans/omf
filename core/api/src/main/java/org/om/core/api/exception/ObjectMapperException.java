package org.om.core.api.exception;

/**
 * Base class for all object mapper exceptions.
 * 
 * @author Jakob Külzer
 * 
 */
public class ObjectMapperException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectMapperException() {
		super();
	}

	public ObjectMapperException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectMapperException(String message) {
		super(message);
	}

	public ObjectMapperException(Throwable cause) {
		super(cause);
	}

}
