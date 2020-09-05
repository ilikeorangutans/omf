package org.om.core.impl.persistence.jcr.test;

import javax.inject.Inject;
import javax.jcr.*;

import com.google.inject.Provider;

public class TestJcrFactory implements Provider<Session> {
	private Session session;

	@Inject
	public TestJcrFactory(Repository repository) {
		try {
			session = repository.login();
		} catch (final RepositoryException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Session get() {
		return session;
	}
}
