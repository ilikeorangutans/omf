package org.om.core.impl.persistence.delegate;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.request.PersistenceRequest;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.persistence.result.MapResult;
import org.om.core.api.persistence.result.PersistenceResult;
import org.om.core.impl.persistence.result.ImmutablePersistenceResult;
import org.om.core.impl.persistence.result.MissingPersistenceResult;
import org.om.core.impl.persistence.result.NoValuePersistenceResult;

/**
 * @author tome
 * @author Jakob Külzer
 */
public class TestingPersistenceAdapter implements PersistenceAdapter {

	private final EntityMapping entityMapping;

	private final TestingPersistenceContext persistenceContext;

	public TestingPersistenceAdapter(EntityMapping entityMapping, PersistenceContext persistenceContext) {
		this.entityMapping = entityMapping;
		this.persistenceContext = (TestingPersistenceContext) (persistenceContext == null ? new TestingPersistenceContext() : persistenceContext);
	}

	public EntityMapping getEntityMapping() {
		return entityMapping;
	}

	public TestingPersistenceAdapter addProperty(String propertyName, Object value) {
		persistenceContext.addProperty(propertyName, value);
		return this;
	}

	public PersistenceResult getProperty(PropertyMapping propertyMapping) {
		if (!persistenceContext.hasProperty(propertyMapping.getPropertyName())) {
			MappedField mappedField = entityMapping.getMappedFields().getFieldByMapping(propertyMapping);
			return MissingPersistenceResult.createMissing(mappedField);
		}
		return new ImmutablePersistenceResult(persistenceContext.getProperty(propertyMapping));
	}

	public boolean canProvide(Mapping mapping) {
		return false; // persistenceContext.hasProperty(mapping.get());
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		persistenceContext.setProperty(propertyMapping, object);
	}

	public void delete() throws ObjectMapperException {
		// do nothing
	}

	public CollectionResult getCollection(CollectionMapping collectionMapping) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistenceResult getProperty(PersistenceRequest request) {
		if (!persistenceContext.hasProperty(request.getPath())) {
			return new NoValuePersistenceResult();
		}
		return new ImmutablePersistenceResult(persistenceContext.getProperty(request.getPath()));
	}

	@Override
	public Object resolve(String id) {
		return id;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public MapResult getMapResult(CollectionMapping collectionMapping) {
		// TODO Auto-generated method stub
		return null;
	}
}
