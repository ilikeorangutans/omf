package org.om.core.impl.test;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Mapped;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.annotation.Property;

@Entity
public class EntityWithPrimitiveProperties {

	public static final int NUMBER_OF_FIELDS = 9;

	@Id
	private String id;

	@Property
	private String fieldWithDefaultSettings;

	@Mapped(missingStrategy = MissingStrategy.DefaultValue)
	@Property(defaultValue = "1234")
	private String fieldWithDefaultValue;

	@Property(name = "customName")
	private String fieldWithCustomName;

	@Mapped(missingStrategy = MissingStrategy.DefaultValue)
	@Property(defaultValue = "default value")
	private String fieldWithMissingStrategy;

	@Mapped(missingException = RuntimeException.class, missingStrategy = MissingStrategy.ThrowException)
	@Property(defaultValue = "custom default value", name = "differentCustomName")
	private String fieldWithAllSettings;

	@Property
	private int primitiveInt;

	@Property
	private Float complexFloat;

	private String unmappedField;

	@Mapped(missingStrategy = MissingStrategy.DefaultValue)
	@Property(defaultValue = "2706")
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

	public void setId(String id) {
		this.id = id;
	}

	public void setPrimitiveInt(int primitiveInt) {
		this.primitiveInt = primitiveInt;
	}

	public void setPrimitiveIntWithDefaultValue(int primitiveIntWithDefaultValue) {
		this.primitiveIntWithDefaultValue = primitiveIntWithDefaultValue;
	}
}
