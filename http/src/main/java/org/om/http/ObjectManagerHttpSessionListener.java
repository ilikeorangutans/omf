package org.om.http;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.om.core.api.session.Session;
import org.om.dao.util.SessionUtil;

/**
 * 
 * @author tome
 * 
 */
public class ObjectManagerHttpSessionListener implements HttpSessionListener {

	private final static String SESSION_OBJECT_NAME = "ObjectManagerSession";

	/**
	 * when a HTTP session is created, create a OM Session and put it in the
	 * HTTP Session
	 */
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		try {
			Session session = SessionUtil.getSession();
			if (null != session) {
				httpSessionEvent.getSession().setAttribute(SESSION_OBJECT_NAME, session);
			} else {
				throw new Exception("Unable to create session");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * when an HTTP session is destroyed, close the OM session if there is one
	 */
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		try {
			Session session = (Session) httpSessionEvent.getSession().getAttribute(SESSION_OBJECT_NAME);
			if (null != session) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
