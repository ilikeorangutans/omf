package org.om.core.impl.persistence.jcr.sessionfactory;

import javax.inject.Inject;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import com.google.inject.Provider;
import com.google.inject.Provides;

public class TestJcrFactory implements JCRSessionFactory, Provider<Session> {

	private Session session;

	@Inject
	public TestJcrFactory(Repository repository) {
		try {
			session = repository.login();
		} catch (RepositoryException e) {
			throw new RuntimeException(e);
		}

	}

	@Provides
	public Session getSession() {
		return session;
	}

	public Session get() {
		return getSession();
	}
}
