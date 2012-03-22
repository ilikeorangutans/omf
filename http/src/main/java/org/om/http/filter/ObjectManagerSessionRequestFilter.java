package org.om.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.om.core.api.session.Session;
import org.om.dao.util.SessionUtil;

/**
 * 
 * @author tome
 * 
 */
public class ObjectManagerSessionRequestFilter implements Filter {

	private final static String SESSION_OBJECT_NAME = "ObjectManagerSession";

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		try {
			/*
			 * get session
			 */
			final Session session = SessionUtil.getSession();
			if (null != session) {
				/*
				 * set attribute
				 */
				servletRequest.setAttribute(SESSION_OBJECT_NAME, session);
				/*
				 * next
				 */
				filterChain.doFilter(servletRequest, servletResponse);
			} else {
				throw new Exception("Unable to create session");
			}
		} catch (final Exception e) {
			throw new ServletException("Exception in doFilter", e);
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
