package com.omf.om.core.mapping.extractor;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.core.mapping.EntityWithPlainProperties;
import com.omf.om.core.mapping.EntityWithoutProperties;

import static org.hamcrest.Matchers.*;

public class PropertyMappingExtractorImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSimpleTypeWithoutProperties() {
		Set<PropertyMapping> extract = new PropertyMappingExtractorImpl().extract(EntityWithoutProperties.class);

		assertThat(extract.isEmpty(), is(true));
	}

	@Test
	public void testSimpleTypeWithBasicProperties() {
		Set<PropertyMapping> extract = new PropertyMappingExtractorImpl()
				.extract(EntityWithPlainProperties.class);

		assertThat(extract.isEmpty(), is(false));
		assertThat(extract.size(), is(EntityWithPlainProperties.NUMBER_OF_FIELDS));
	}

}
