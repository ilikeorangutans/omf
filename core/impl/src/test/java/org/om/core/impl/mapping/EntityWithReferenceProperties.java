package org.om.core.impl.mapping;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

@Entity
public class EntityWithReferenceProperties {

	@Id
	private String id;

	@Property
	private EntityWithPrimitiveProperties entityWithPrimitiveProperties;

	public EntityWithReferenceProperties() {
	}

	public EntityWithPrimitiveProperties getEntityWithPrimitiveProperties() {
		return entityWithPrimitiveProperties;
	}

	public String getId() {
		return id;
	}
}
