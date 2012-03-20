package org.om.core.impl.persistence.jcr.sessionfactory.impl;

import java.io.File;

import javax.jcr.Repository;
import javax.jcr.Session;

import org.apache.jackrabbit.core.TransientRepository;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

/**
 * @author tome
 */
public class TransientRepositoryJCRSessionFactory implements JCRSessionFactory {
	/**
	 * path
	 */
	private final String path;

	/**
	 * ctor
	 */
	public TransientRepositoryJCRSessionFactory(String path) {
		this.path = path;
	}

	public Session getSession() throws JCRException {
		try {
			final File file = new File(".");
			final Repository repository = new TransientRepository(new File(file, path));
			return repository.login();
		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
