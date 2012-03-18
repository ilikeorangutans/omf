package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.om.core.impl.persistence.jcr.api.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * @author tome
 *         <p>
 *         This test needs JackRabbit 2.4.0 running in standalone mode on
 *         localhost
 *         </p>
 */
public class ParameterizedJCRSessionFactory implements JCRSessionFactory {
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
	public ParameterizedJCRSessionFactory(String url, String workspace, String username, String password) {
		this.url = url;
		this.password = password;
		this.username = username;
		this.workspace = workspace;

	}

	public Session getSession() throws JCRException {
		try {
			final Repository repository = JcrUtils.getRepository(url);
			final SimpleCredentials creds = new SimpleCredentials(username, password.toCharArray());
			return repository.login(creds, workspace);
		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
