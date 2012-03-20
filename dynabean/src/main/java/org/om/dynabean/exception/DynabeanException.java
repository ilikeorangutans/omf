package org.om.dynabean.exception;

/**
 * @author tome
 */
public class DynabeanException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DynabeanException(Exception e) {
		super(e);
	}

	public DynabeanException(String e) {
		super(e);
	}

	public DynabeanException(String s, Exception e) {
		super(s, e);
	}
}