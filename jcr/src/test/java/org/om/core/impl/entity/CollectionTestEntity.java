package org.om.core.impl.entity;

import java.util.List;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

@Entity
public class CollectionTestEntity {

	@Id
	@Property
	private String id;

	@Property
	@Collection(targetType = ChildEntity.class)
	private List<ChildEntity> list;

	public CollectionTestEntity() {
	}

	public String getId() {
		return id;
	}

	public List<ChildEntity> getList() {
		return list;
	}

}
