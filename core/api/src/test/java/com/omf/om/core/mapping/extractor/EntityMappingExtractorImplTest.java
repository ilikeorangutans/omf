package com.omf.om.core.mapping.extractor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.mapping.EntityWithPlainProperties;
import com.omf.om.core.mapping.EntityWithoutProperties;

import static org.hamcrest.Matchers.*;

public class EntityMappingExtractorImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = MappingException.class)
	public void testEntityWithoutAnnotation() throws Exception {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(getClass());
	}

	@Test
	public void testEntityWithoutProperties() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithoutProperties.class);

		assertThat(mapping, notNullValue());
		assertEquals(EntityWithoutProperties.class, mapping.getTypeClass());
	}

	@Test
	public void testEntityWithBasicProperties() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPlainProperties.class);

		assertThat(mapping, notNullValue());
		assertEquals(EntityWithPlainProperties.class, mapping.getTypeClass());
		assertThat(mapping.getPropertyMappings().size(), is(EntityWithPlainProperties.NUMBER_OF_FIELDS));
	}

}
