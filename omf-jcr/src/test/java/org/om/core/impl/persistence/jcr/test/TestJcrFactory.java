package org.om.core.impl.persistence.jcr.test;

import javax.inject.Inject;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import com.google.inject.Provider;

public class TestJcrFactory implements Provider<Session> {

	private Session session;

	@Inject
	public TestJcrFactory(Repository repository) {
		try {
			session = repository.login();
		} catch (RepositoryException e) {
			throw new RuntimeException(e);
		}

	}

	public Session get() {
		return session;
	}
}
