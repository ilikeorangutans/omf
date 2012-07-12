package org.om.core.impl.persistence.interceptor.handler;

import java.util.Collection;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.request.PersistenceRequest;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.persistence.result.PersistenceResult;
import org.om.core.impl.persistence.result.ImmutableCollectionPersistenceResult;
import org.om.core.impl.persistence.result.ImmutablePersistenceResult;

/**
 * {@link PersistenceAdapter} that will just pass through the object given at
 * construction time. Used for testing.
 * 
 * @author Jakob Külzer
 * 
 */
public class TestingPassThroughPersistenceAdapter implements PersistenceAdapter {

	private final Object object;

	public TestingPassThroughPersistenceAdapter(Object object) {
		this.object = object;
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		throw new IllegalStateException("Not implemented.");
	}

	public PersistenceResult getProperty(PropertyMapping mapping) throws ObjectMapperException {
		return new ImmutablePersistenceResult(object);
	}

	public CollectionResult getCollection(CollectionMapping collectionMapping) {
		return new ImmutableCollectionPersistenceResult((Collection<?>) object);
	}

	public boolean canProvide(Mapping mapping) throws ObjectMapperException {
		return true;
	}

	public void delete() throws ObjectMapperException {
		throw new IllegalStateException("Not implemented.");
	}

	@Override
	public PersistenceResult getProperty(PersistenceRequest request) {
		return new ImmutablePersistenceResult(object);
	}

}
