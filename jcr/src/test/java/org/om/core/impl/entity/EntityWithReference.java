package org.om.core.impl.entity;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

@Entity
public class EntityWithReference {

	@Id
	@Property
	private String id;

	@Property
	private TestEntity testEntity;

	public EntityWithReference() {
	}

	public String getId() {
		return id;
	}

	public TestEntity getTestEntity() {
		return testEntity;
	}

}
