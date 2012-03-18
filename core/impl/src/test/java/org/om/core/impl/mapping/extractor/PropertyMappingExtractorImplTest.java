package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.mapping.PropertyMap;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.EntityWithReferenceProperties;
import org.om.core.impl.mapping.EntityWithoutProperties;

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

		assertThat(mapping, notNullValue());
		assertThat(mapping.getFieldname(), is("id"));
	}

	@Test
	public void testExtractReferenceProperty() {
		PropertyMap extract = new PropertyMappingExtractorImpl().extract(EntityWithReferenceProperties.class);
		PropertyMapping mapping = extract.getProperty("entityWithPrimitiveProperties");

		assertThat(mapping, notNullValue());
		assertEquals(EntityWithPrimitiveProperties.class, mapping.getPropertyType());
	}

}
