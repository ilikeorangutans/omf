package com.omf.om.core.mapping;

import com.omf.om.api.annotation.Entity;
import com.omf.om.api.annotation.Property;
import com.omf.om.api.annotation.PropertyMissingStrategy;

@Entity
public class EntityWithPlainProperties {

	public static final int NUMBER_OF_FIELDS = 7;

	@Property
	private String fieldWithDefaultSettings;

	@Property(defaultValue = "1234")
	private String fieldWithDefaultValue;

	@Property(name = "customName")
	private String fieldWithCustomName;

	@Property(missingStrategy = PropertyMissingStrategy.DefaultValue)
	private String fieldWithMissingStrategy;

	@Property(defaultValue = "custom default value", missingException = RuntimeException.class, missingStrategy = PropertyMissingStrategy.ThrowException, name = "customName")
	private String fieldWithAllSettings;

	@Property
	private int primitiveInt;

	@Property
	private Float complexFloat;

	public EntityWithPlainProperties() {
	}
}
