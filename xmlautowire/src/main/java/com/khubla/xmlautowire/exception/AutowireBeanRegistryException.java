package com.khubla.xmlautowire.exception;

/**
 * @author tome
 */
public class AutowireBeanRegistryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AutowireBeanRegistryException(Exception e) {
		super(e);
	}

	public AutowireBeanRegistryException(String e) {
		super(e);
	}

	public AutowireBeanRegistryException(String s, Exception e) {
		super(s, e);
	}
}