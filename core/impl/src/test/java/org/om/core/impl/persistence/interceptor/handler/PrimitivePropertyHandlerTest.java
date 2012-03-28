package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.ItemMap;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.extractor.ItemMappingExtractorImpl;

public class PrimitivePropertyHandlerTest {

	@Test
	public void testNullInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		Object retrieve = handler.retrieve(mapping.getField("fieldWithDefaultSettings"), null);

		assertThat(retrieve, nullValue());
	}

	@Test
	public void testWithStringInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		String retrieve = (String) handler.retrieve(mapping.getField("fieldWithDefaultSettings"), "I'm a String!");

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is("I'm a String!"));
	}

	@Test
	public void testIntegerFieldWithStringInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		Integer retrieve = (Integer) handler.retrieve(mapping.getField("primitiveInt"), "1234");

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is(1234));
	}

	@Test(expected = NumberFormatException.class)
	public void testIntegerFieldWithInvalidStringInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		Integer retrieve = (Integer) handler.retrieve(mapping.getField("primitiveInt"), "BAM");

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is(1234));
	}

}
