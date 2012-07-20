package org.om.core.impl.mapping;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.LookupMode;
import org.om.core.api.annotation.Property;

@Entity
public class EntityWithReferenceProperties {

	@Id
	private String id;

	@Property
	private EntityWithPrimitiveProperties entityWithPrimitiveProperties;

	@Property(name = "foo/bar")
	private EntityWithPrimitiveProperties customNamedReference;

	@Property(name = "foobar", lookupMode = LookupMode.Direct)
	private EntityWithPrimitiveProperties customLookupMode;

	public EntityWithReferenceProperties() {
	}

	public EntityWithPrimitiveProperties getEntityWithPrimitiveProperties() {
		return entityWithPrimitiveProperties;
	}

	public String getId() {
		return id;
	}

	public EntityWithPrimitiveProperties getCustomLookupMode() {
		return customLookupMode;
	}

	public EntityWithPrimitiveProperties getCustomNamedReference() {
		return customNamedReference;
	}
}
