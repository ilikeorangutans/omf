package org.om.core.impl.test;

import org.om.core.api.annotation.*;

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
	@Property
	@Mapped(implementationType = EntityImplementingInterface.class)
	private MyInterface myInterface;

	public EntityWithReferenceProperties() {
	}

	public EntityWithPrimitiveProperties getCustomLookupMode() {
		return customLookupMode;
	}

	public EntityWithPrimitiveProperties getCustomNamedReference() {
		return customNamedReference;
	}

	public EntityWithPrimitiveProperties getEntityWithPrimitiveProperties() {
		return entityWithPrimitiveProperties;
	}

	public String getId() {
		return id;
	}

	public MyInterface getMyInterface() {
		return myInterface;
	}
}
