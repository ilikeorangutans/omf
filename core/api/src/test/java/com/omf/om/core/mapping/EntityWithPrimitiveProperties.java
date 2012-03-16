package com.omf.om.core.mapping;

import com.omf.om.api.annotation.Entity;
import com.omf.om.api.annotation.Property;
import com.omf.om.api.annotation.PropertyMissingStrategy;

@Entity
public class EntityWithPrimitiveProperties {

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

	private String unmappedField;

	public EntityWithPrimitiveProperties() {
		unmappedField = "unmapped";
	}

	public String getUnmappedField() {
		return unmappedField;
	}

	public String getFieldWithDefaultSettings() {
		return fieldWithDefaultSettings;
	}

	public void setFieldWithDefaultSettings(String fieldWithDefaultSettings) {
		this.fieldWithDefaultSettings = fieldWithDefaultSettings;
	}

	public String getFieldWithDefaultValue() {
		return fieldWithDefaultValue;
	}

	public void setFieldWithDefaultValue(String fieldWithDefaultValue) {
		this.fieldWithDefaultValue = fieldWithDefaultValue;
	}

	public String getFieldWithCustomName() {
		return fieldWithCustomName;
	}

	public void setFieldWithCustomName(String fieldWithCustomName) {
		this.fieldWithCustomName = fieldWithCustomName;
	}

	public String getFieldWithMissingStrategy() {
		return fieldWithMissingStrategy;
	}

	public void setFieldWithMissingStrategy(String fieldWithMissingStrategy) {
		this.fieldWithMissingStrategy = fieldWithMissingStrategy;
	}

	public String getFieldWithAllSettings() {
		return fieldWithAllSettings;
	}

	public void setFieldWithAllSettings(String fieldWithAllSettings) {
		this.fieldWithAllSettings = fieldWithAllSettings;
	}

	public int getPrimitiveInt() {
		return primitiveInt;
	}

	public void setPrimitiveInt(int primitiveInt) {
		this.primitiveInt = primitiveInt;
	}

	public Float getComplexFloat() {
		return complexFloat;
	}

	public void setComplexFloat(Float complexFloat) {
		this.complexFloat = complexFloat;
	}
}
