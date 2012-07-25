package org.om.core.impl.entity;

import java.util.List;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;

@Entity
public class CollectionTestEntity {

	@Id
	private String id;

	@Collection(targetType = ChildEntity.class, location = "./")
	private List<ChildEntity> list;

	@Collection(targetType = String.class)
	private List<String> listOfStrings;

	public CollectionTestEntity() {
	}

	public String getId() {
		return id;
	}

	public List<ChildEntity> getList() {
		return list;
	}

	public List<String> getListOfStrings() {
		return listOfStrings;
	}
}
