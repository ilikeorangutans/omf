package org.om.core.impl.persistence.interceptor.handler;

import java.util.Collection;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceDelegate;

public class TestingPassThroughPersistenceDelegate implements PersistenceDelegate {

	private final Object object;

	public TestingPassThroughPersistenceDelegate(Object object) {
		this.object = object;
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		throw new IllegalStateException("Not implemented.");
	}

	public Object getProperty(PropertyMapping mapping) throws ObjectMapperException {
		return object;
	}

	public Collection<?> getCollection(CollectionMapping collectionMapping) {
		return (Collection<?>) object;
	}

	public boolean canProvide(Mapping mapping) throws ObjectMapperException {
		return true;
	}

	public void delete() throws ObjectMapperException {
		throw new IllegalStateException("Not implemented.");
	}

}
