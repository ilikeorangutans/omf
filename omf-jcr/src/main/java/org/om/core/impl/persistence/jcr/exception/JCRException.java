package org.om.core.impl.persistence.jcr.exception;

/**
 * @author tome
 */
public class JCRException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JCRException(Exception e) {
		super(e);
	}

	public JCRException(String e) {
		super(e);
	}

	public JCRException(String s, Exception e) {
		super(s, e);
	}
}