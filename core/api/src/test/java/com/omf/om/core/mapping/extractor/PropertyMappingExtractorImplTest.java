package com.omf.om.core.mapping.extractor;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.omf.om.api.mapping.PropertyMap;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.core.mapping.EntityWithPrimitiveProperties;
import com.omf.om.core.mapping.EntityWithoutProperties;

import static org.hamcrest.Matchers.*;

public class PropertyMappingExtractorImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSimpleTypeWithoutProperties() {
		PropertyMap extract = new PropertyMappingExtractorImpl().extract(EntityWithoutProperties.class);

		assertThat(extract.isEmpty(), is(true));
	}

	@Test
	public void testSimpleTypeWithBasicProperties() {
		PropertyMap extract = new PropertyMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);

		assertThat(extract.isEmpty(), is(false));
		assertThat(extract.getSize(), is(EntityWithPrimitiveProperties.NUMBER_OF_FIELDS));
	}

	@Test
	public void testExtractIdProperty() {
		PropertyMap extract = new PropertyMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PropertyMapping mapping = extract.getIdProperty();

	}

}
