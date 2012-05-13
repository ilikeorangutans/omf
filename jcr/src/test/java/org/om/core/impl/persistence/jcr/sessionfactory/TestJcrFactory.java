package org.om.core.impl.persistence.jcr.sessionfactory;

import javax.inject.Inject;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

import com.google.inject.Provider;
import com.google.inject.Provides;

public class TestJcrFactory implements JCRSessionFactory, Provider<Session> {

	@Inject
	public TestJcrFactory(Repository repository) {
		this.repository = repository;
	}

	private final Repository repository;

	@Provides
	public Session getSession() {
		try {
			return repository.login();
		} catch (RepositoryException e) {
			throw new RuntimeException(e);
		}
	}

	public Session get() {
		return getSession();
	}
}
