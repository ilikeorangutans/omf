package org.om.core.impl.persistence.interceptor.handler;

import java.util.*;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.request.*;
import org.om.core.api.persistence.result.*;
import org.om.core.impl.persistence.result.*;

/**
 * {@link PersistenceAdapter} that will just pass through the object given at construction time.
 * Used for testing.
 *
 * @author Jakob Külzer
 */
public class TestingPassThroughPersistenceAdapter implements PersistenceAdapter {
	private final Object object;

	public TestingPassThroughPersistenceAdapter(Object object) {
		this.object = object;
	}

	public boolean canProvide(Mapping mapping) throws ObjectMapperException {
		return true;
	}

	@Override
	public void delete() throws ObjectMapperException {
		throw new IllegalStateException("Not implemented.");
	}

	@Override
	public CollectionResult getCollection(CollectionMapping collectionMapping) {
		return new ImmutableCollectionPersistenceResult((Collection<?>) object);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapResult getMapResult(CollectionMapping collectionMapping) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistenceResult getProperty(PersistenceRequest request) {
		return new ImmutablePersistenceResult(object);
	}

	@Override
	public PersistenceResult getProperty(PropertyMapping mapping) throws ObjectMapperException {
		return new ImmutablePersistenceResult(object);
	}

	@Override
	public Object resolve(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		throw new IllegalStateException("Not implemented.");
	}
}
