package org.om.core.impl.test;

import java.util.*;

import org.om.core.api.annotation.*;
import org.om.core.api.annotation.Collection;

@Entity
public class EntityWithInvalidCollectionMapping {
	@Id
	@Property
	private String id;
	@Property
	@Collection(targetType = NotAnEntity.class)
	private List<NotAnEntity> invalidCollection;

	public String getId() {
		return id;
	}

	public List<NotAnEntity> getInvalidCollection() {
		return invalidCollection;
	}
}
