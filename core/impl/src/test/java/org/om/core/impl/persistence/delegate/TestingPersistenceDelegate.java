package org.om.core.impl.persistence.delegate;

import java.util.Collection;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceAdapter;

/**
 * @author tome
 * @author Jakob Külzer
 */
public class TestingPersistenceDelegate implements PersistenceAdapter {

	private final EntityMapping entityMapping;

	private final TestingPersistenceContext persistenceContext;

	public TestingPersistenceDelegate(EntityMapping entityMapping, PersistenceContext persistenceContext) {
		this.entityMapping = entityMapping;
		this.persistenceContext = (TestingPersistenceContext) (persistenceContext == null ? new TestingPersistenceContext() : persistenceContext);
	}

	public EntityMapping getEntityMapping() {
		return entityMapping;
	}

	public TestingPersistenceDelegate addProperty(String propertyName, Object value) {
		persistenceContext.addProperty(propertyName, value);
		return this;
	}

	public Object getProperty(PropertyMapping propertyMapping) {
		return persistenceContext.getProperty(propertyMapping);
	}

	public boolean canProvide(Mapping mapping) {
		return persistenceContext.hasProperty(mapping.getFieldname());
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		persistenceContext.setProperty(propertyMapping, object);
	}

	public void delete() throws ObjectMapperException {
		// do nothing
	}

	public Collection<?> getCollection(CollectionMapping collectionMapping) {
		// TODO Auto-generated method stub
		return null;
	}

}
