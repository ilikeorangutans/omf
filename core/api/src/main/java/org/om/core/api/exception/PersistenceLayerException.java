package org.om.core.api.exception;

public class PersistenceLayerException extends ObjectMapperException {

	private static final long serialVersionUID = 1L;

	public PersistenceLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceLayerException(String message) {
		super(message);
	}

	public PersistenceLayerException(Throwable cause) {
		super(cause);
	}

}
