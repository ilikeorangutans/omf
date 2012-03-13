package com.omf.om.core.persistence.delegate;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.session.Session;

public class TestingPersistenceDelegate implements PersistenceDelegate {

	private final EntityMapping entityMapping;
	private final Session session;

	private final TestingPersistenceContext persistenceContext;

	public TestingPersistenceDelegate(Session session, EntityMapping entityMapping, PersistenceContext persistenceContext) {
		this.session = session;
		this.entityMapping = entityMapping;
		this.persistenceContext = (TestingPersistenceContext) (persistenceContext == null ? new TestingPersistenceContext() : persistenceContext);
	}

	public EntityMapping getEntityMapping() {
		return entityMapping;
	}

	public Session getSession() {
		return session;
	}

	public Object getProperty(PropertyMapping propertyMapping) {
		return persistenceContext.getProperty(propertyMapping);
	}

	public TestingPersistenceDelegate addProperty(String propertyName, Object value) {
		persistenceContext.addProperty(propertyName, value);
		return this;
	}

}
