package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * @author tome
 *         <p>
 *         This test needs JackRabbit 2.4.0 running in standalone mode on
 *         localhost
 *         </p>
 */
public class ParameterizedSessionFactory implements SessionFactory {
	/**
	 * url
	 */
	private final String url;

	/**
	 * username
	 */
	private final String username;
	/**
	 * password
	 */
	private final String password;
	/**
	 * workspace
	 */
	private final String workspace;

	/**
	 * ctor
	 */
	public ParameterizedSessionFactory(String url, String workspace, String username, String password) {
		this.url = url;
		this.password = password;
		this.username = username;
		this.workspace = workspace;

	}

	public Session getSession() throws JCRException {
		try {
			Repository repository = JcrUtils.getRepository(url);
			SimpleCredentials creds = new SimpleCredentials(this.username, this.password.toCharArray());
			return repository.login(creds, workspace);
		} catch (Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
