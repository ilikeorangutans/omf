package org.om.core.impl.mapping;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;
import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.exception.PropertyMissingException;

@Entity
public class EntityWithPrimitiveProperties {

	public void setPrimitiveIntWithDefaultValue(int primitiveIntWithDefaultValue) {
		this.primitiveIntWithDefaultValue = primitiveIntWithDefaultValue;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static final int NUMBER_OF_FIELDS = 9;

	@Id
	@Property
	private String id;

	@Property
	private String fieldWithDefaultSettings;

	@Property(defaultValue = "1234")
	private String fieldWithDefaultValue;

	@Property(name = "customName")
	private String fieldWithCustomName;

	@Property(missingStrategy = PropertyMissingStrategy.DefaultValue, defaultValue = "default value")
	private String fieldWithMissingStrategy;

	@Property(defaultValue = "custom default value", missingException = PropertyMissingException.class, missingStrategy = PropertyMissingStrategy.ThrowException, name = "differentCustomName")
	private String fieldWithAllSettings;

	@Property
	private int primitiveInt;

	@Property
	private Float complexFloat;

	private String unmappedField;

	@Property(defaultValue = "2706", missingStrategy = PropertyMissingStrategy.DefaultValue)
	private int primitiveIntWithDefaultValue;

	public EntityWithPrimitiveProperties() {
		unmappedField = "unmapped";
	}

	public Float getComplexFloat() {
		return complexFloat;
	}

	public String getFieldWithAllSettings() {
		return fieldWithAllSettings;
	}

	public String getFieldWithCustomName() {
		return fieldWithCustomName;
	}

	public String getFieldWithDefaultSettings() {
		return fieldWithDefaultSettings;
	}

	public String getFieldWithDefaultValue() {
		return fieldWithDefaultValue;
	}

	public String getFieldWithMissingStrategy() {
		return fieldWithMissingStrategy;
	}

	public String getId() {
		return id;
	}

	public int getPrimitiveInt() {
		return primitiveInt;
	}

	public int getPrimitiveIntWithDefaultValue() {
		return primitiveIntWithDefaultValue;
	}

	public String getUnmappedField() {
		return unmappedField;
	}

	public void setComplexFloat(Float complexFloat) {
		this.complexFloat = complexFloat;
	}

	public void setFieldWithAllSettings(String fieldWithAllSettings) {
		this.fieldWithAllSettings = fieldWithAllSettings;
	}

	public void setFieldWithCustomName(String fieldWithCustomName) {
		this.fieldWithCustomName = fieldWithCustomName;
	}

	public void setFieldWithDefaultSettings(String fieldWithDefaultSettings) {
		this.fieldWithDefaultSettings = fieldWithDefaultSettings;
	}

	public void setFieldWithDefaultValue(String fieldWithDefaultValue) {
		this.fieldWithDefaultValue = fieldWithDefaultValue;
	}

	public void setFieldWithMissingStrategy(String fieldWithMissingStrategy) {
		this.fieldWithMissingStrategy = fieldWithMissingStrategy;
	}

	public void setPrimitiveInt(int primitiveInt) {
		this.primitiveInt = primitiveInt;
	}
}
