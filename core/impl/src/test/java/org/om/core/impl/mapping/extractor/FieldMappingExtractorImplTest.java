package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.annotation.LookupMode;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.exception.MissingException;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.extractor.FieldMappingExtractor;
import org.om.core.api.mapping.field.IdMapping;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.api.mapping.field.ReferenceMapping;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.EntityWithReferenceProperties;

public class FieldMappingExtractorImplTest {

	private FieldMappingExtractor extractor = new FieldMappingExtractorImpl();

	@Test
	public void testExtractIdProperty() throws Exception {
		MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("id"));

		assertThat(mappedField, notNullValue());
		assertThat(mappedField.getName(), is("id"));

		assertThat(mappedField.getMapping(), instanceOf(IdMapping.class));
	}

	@Test
	public void testExtractFieldWithDefaultSettings() throws Exception {
		MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("fieldWithDefaultSettings"));

		assertThat(mappedField, notNullValue());
		assertThat(mappedField.getName(), is("fieldWithDefaultSettings"));
		assertThat(mappedField.getMissingStrategy(), is(MissingStrategy.ReturnNull));
		assertEquals(MissingException.class, mappedField.getMissingException());
		assertThat(mappedField.getMapping(), instanceOf(PropertyMapping.class));
	}

	@Test
	public void testExtractFieldWithDefaultValue() throws Exception {
		MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("fieldWithDefaultValue"));

		assertThat(mappedField.getName(), is("fieldWithDefaultValue"));
		assertThat(mappedField.getMissingStrategy(), is(MissingStrategy.DefaultValue));
		assertEquals(MissingException.class, mappedField.getMissingException());
		assertThat(mappedField.getMapping(), instanceOf(PropertyMapping.class));
		assertThat(((PropertyMapping) mappedField.getMapping()).getDefaultValue(), is("1234"));
	}

	@Test
	public void testExtractFieldWithAllSettings() throws Exception {
		MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("fieldWithAllSettings"));

		assertThat(mappedField.getName(), is("fieldWithAllSettings"));
		assertThat(mappedField.getMissingStrategy(), is(MissingStrategy.ThrowException));
		assertEquals(RuntimeException.class, mappedField.getMissingException());
		assertThat(mappedField.getMapping(), instanceOf(PropertyMapping.class));
		assertThat(((PropertyMapping) mappedField.getMapping()).getDefaultValue(), is("custom default value"));
	}

	@Test
	public void testExtractFieldWithReference() throws Exception {
		MappedField mappedField = extractor.extract(EntityWithReferenceProperties.class.getDeclaredField("entityWithPrimitiveProperties"));

		assertThat(mappedField.getName(), is("entityWithPrimitiveProperties"));
		assertThat(mappedField.getMapping(), instanceOf(ReferenceMapping.class));
		ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();
		assertThat(mapping.getPath(), is("entityWithPrimitiveProperties"));

	}

	@Test
	public void testExtractFieldWithReferenceAndCustomName() throws Exception {
		MappedField mappedField = extractor.extract(EntityWithReferenceProperties.class.getDeclaredField("customNamedReference"));

		assertThat(mappedField.getName(), is("customNamedReference"));
		assertThat(mappedField.getMapping(), instanceOf(ReferenceMapping.class));
		ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();
		assertThat(mapping.getPath(), is("foo/bar"));

	}

	@Test
	public void testExtractReferenceFieldWithCustomLookup() throws Exception {
		MappedField mappedField = extractor.extract(EntityWithReferenceProperties.class.getDeclaredField("customLookupMode"));

		assertThat(mappedField.getName(), is("customLookupMode"));
		assertThat(mappedField.getMapping(), instanceOf(ReferenceMapping.class));
		ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();
		assertThat(mapping.getPath(), is("foobar"));
		assertThat(mapping.getLookupMode(), is(LookupMode.Direct));

	}

}
