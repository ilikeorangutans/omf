package org.om.dao.impl;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;
import org.om.core.api.annotation.PropertyMissingStrategy;

@Entity
public class TestEntity {

	@Id
	@Property
	private String id;

	@Property
	private String foobar;

	@Property(defaultValue = "3131", missingStrategy = PropertyMissingStrategy.DefaultValue, name = "mycoolfield")
	private int blargh;

	public TestEntity() {
	}

	public String getFoobar() {
		return foobar;
	}

	public int getBlargh() {
		return blargh;
	}

	public String getId() {
		return id;
	}
}
