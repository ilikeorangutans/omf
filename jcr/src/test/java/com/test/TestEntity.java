package com.test;

import com.omf.om.api.annotation.Entity;
import com.omf.om.api.annotation.Id;
import com.omf.om.api.annotation.Property;
import com.omf.om.api.annotation.PropertyMissingStrategy;

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
