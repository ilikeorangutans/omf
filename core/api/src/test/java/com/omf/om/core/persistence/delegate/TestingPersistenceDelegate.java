package com.omf.om.core.persistence.delegate;

import java.util.HashMap;
import java.util.Map;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.session.Session;

public class TestingPersistenceDelegate implements PersistenceDelegate {

	private final EntityMapping entityMapping;
	private final Session session;

	private final Map<String, Object> properties;

	public TestingPersistenceDelegate(Session session, EntityMapping entityMapping) {
		this.session = session;
		this.entityMapping = entityMapping;
		properties = new HashMap<String, Object>();
	}

	public EntityMapping getEntityMapping() {
		return entityMapping;
	}

	public Session getSession() {
		return session;
	}

	public Object getProperty(PropertyMapping propertyMapping) {
		return properties.get(propertyMapping.getPropertyName());
	}

	public TestingPersistenceDelegate addProperty(String propertyName, Object value) {
		properties.put(propertyName, value);
		return this;
	}

}
