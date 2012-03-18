package org.om.jcr2pojo.exception;

/**
 * @author tome
 */
public class JCR2POJOException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JCR2POJOException(Exception e) {
		super(e);
	}

	public JCR2POJOException(String e) {
		super(e);
	}

	public JCR2POJOException(String s, Exception e) {
		super(s, e);
	}
}