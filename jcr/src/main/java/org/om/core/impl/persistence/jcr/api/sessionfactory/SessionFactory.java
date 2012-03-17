package org.om.core.impl.persistence.jcr.api.sessionfactory;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * A factory for JCR sessions.
 * 
 * @author tome
 */
public interface SessionFactory {
	/**
	 * get a JCR Session
	 */
	Session getSession() throws JCRException;
}
