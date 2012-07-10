package org.om.core.api.exception;

public class MissingException extends ObjectMapperException {

	private static final long serialVersionUID = 1L;

	public MissingException(String message) {
		super(message);
	}

	public MissingException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public MissingException(Throwable throwable) {
		super(throwable);
	}

}
