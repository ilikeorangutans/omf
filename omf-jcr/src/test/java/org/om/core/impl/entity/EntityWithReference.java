package org.om.core.impl.entity;

import org.om.core.api.annotation.*;

@Entity
public class EntityWithReference {
	@Id
	private String id;
	@Property
	private TestEntity testEntity;
	@Mapped(missingStrategy = MissingStrategy.ThrowException)
	@Property
	private TestEntity referenceThrowingExceptionOnMissing;

	public EntityWithReference() {
	}

	public String getId() {
		return id;
	}

	public TestEntity getReferenceThrowingExceptionOnMissing() {
		return referenceThrowingExceptionOnMissing;
	}

	public TestEntity getTestEntity() {
		return testEntity;
	}
}
