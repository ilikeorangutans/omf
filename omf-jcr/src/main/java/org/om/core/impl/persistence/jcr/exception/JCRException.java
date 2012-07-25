package org.om.core.impl.persistence.jcr.exception;

/**
 * @author tome
 */
public class JcrException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JcrException(Exception e) {
		super(e);
	}

	public JcrException(String e) {
		super(e);
	}

	public JcrException(String s, Exception e) {
		super(s, e);
	}
}