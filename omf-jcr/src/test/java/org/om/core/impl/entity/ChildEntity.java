package org.om.core.impl.entity;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

@Entity
public class ChildEntity {

	@Id
	private String id;

	@Property
	private String value;

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
}
