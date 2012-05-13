package org.om.core.impl.entity;

import java.util.List;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

@Entity
public class CollectionTestEntity {

	@Id
	@Property
	private String id;

	private List<ChildEntity> list;
	
	public String getId() {
		return id;
	}

}
