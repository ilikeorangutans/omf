package com.test;

import com.omf.om.api.annotation.Entity;
import com.omf.om.api.annotation.Property;

@Entity
public class TestEntity {

	@Property
	private String foobar;

	public TestEntity() {
	}

	public String getFoobar() {
		return foobar;
	}

}
