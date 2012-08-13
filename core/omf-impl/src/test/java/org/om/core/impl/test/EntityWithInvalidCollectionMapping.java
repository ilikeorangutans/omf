package org.om.core.impl.test;

import java.util.List;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

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
