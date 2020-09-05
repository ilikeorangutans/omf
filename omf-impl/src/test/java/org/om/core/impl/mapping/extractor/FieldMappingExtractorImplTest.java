package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.om.core.api.annotation.*;
import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.extractor.*;
import org.om.core.api.mapping.field.*;
import org.om.core.impl.test.*;

public class FieldMappingExtractorImplTest {
	private final FieldMappingExtractor extractor = new FieldMappingExtractorImpl();

	@Test
	public void testExtractFieldWithAllSettings() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("fieldWithAllSettings"));
		assertThat(mappedField.getName(), is("fieldWithAllSettings"));
		assertThat(mappedField.getMissingStrategy(), is(MissingStrategy.ThrowException));
		assertEquals(RuntimeException.class, mappedField.getMissingException());
		assertThat(mappedField.getMapping(), instanceOf(PropertyMapping.class));
		assertThat(((PropertyMapping) mappedField.getMapping()).getDefaultValue(), is("custom default value"));
	}

	@Test
	public void testExtractFieldWithDefaultSettings() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("fieldWithDefaultSettings"));
		assertThat(mappedField, notNullValue());
		assertThat(mappedField.getName(), is("fieldWithDefaultSettings"));
		assertThat(mappedField.getMissingStrategy(), is(MissingStrategy.ReturnNull));
		assertEquals(MissingException.class, mappedField.getMissingException());
		assertThat(mappedField.getMapping(), instanceOf(PropertyMapping.class));
	}

	@Test
	public void testExtractFieldWithDefaultValue() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("fieldWithDefaultValue"));
		assertThat(mappedField.getName(), is("fieldWithDefaultValue"));
		assertThat(mappedField.getMissingStrategy(), is(MissingStrategy.DefaultValue));
		assertEquals(MissingException.class, mappedField.getMissingException());
		assertThat(mappedField.getMapping(), instanceOf(PropertyMapping.class));
		assertThat(((PropertyMapping) mappedField.getMapping()).getDefaultValue(), is("1234"));
	}

	@Test
	public void testExtractFieldWithReference() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithReferenceProperties.class.getDeclaredField("entityWithPrimitiveProperties"));
		assertThat(mappedField.getName(), is("entityWithPrimitiveProperties"));
		assertThat(mappedField.getMapping(), instanceOf(ReferenceMapping.class));
		final ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();
		assertThat(mapping.getPath(), is("entityWithPrimitiveProperties"));
	}

	@Test
	public void testExtractFieldWithReferenceAndCustomName() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithReferenceProperties.class.getDeclaredField("customNamedReference"));
		assertThat(mappedField.getName(), is("customNamedReference"));
		assertThat(mappedField.getMapping(), instanceOf(ReferenceMapping.class));
		final ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();
		assertThat(mapping.getPath(), is("foo/bar"));
	}

	@Test
	public void testExtractIdProperty() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithPrimitiveProperties.class.getDeclaredField("id"));
		assertThat(mappedField, notNullValue());
		assertThat(mappedField.getName(), is("id"));
		assertThat(mappedField.getMapping(), instanceOf(IdMapping.class));
	}

	@Test
	public void testExtractReferenceFieldWithCustomLookup() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithReferenceProperties.class.getDeclaredField("customLookupMode"));
		assertThat(mappedField.getName(), is("customLookupMode"));
		assertThat(mappedField.getMapping(), instanceOf(ReferenceMapping.class));
		final ReferenceMapping mapping = (ReferenceMapping) mappedField.getMapping();
		assertThat(mapping.getPath(), is("foobar"));
		assertThat(mapping.getLookupMode(), is(LookupMode.Direct));
	}

	@Test
	public void testExtractReferenceFieldWithDifferingImplementationType() throws Exception {
		final MappedField mappedField = extractor.extract(EntityWithReferenceProperties.class.getDeclaredField("myInterface"));
		assertThat(mappedField.getName(), is("myInterface"));
		assertThat(mappedField.getMapping(), instanceOf(ReferenceMapping.class));
		final Mapping mapping = mappedField.getMapping();
		assertEquals(MyInterface.class, mapping.getDeclaredType());
		assertEquals(EntityImplementingInterface.class, mapping.getImplementationType());
	}
}
