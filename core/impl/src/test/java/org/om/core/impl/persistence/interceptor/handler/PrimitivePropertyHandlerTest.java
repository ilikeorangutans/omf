package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.ItemMap;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.extractor.ItemMappingExtractorImpl;
import org.om.core.impl.persistence.delegate.TestingPersistenceDelegate;

public class PrimitivePropertyHandlerTest {

	@Test
	public void testNullInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		PersistenceDelegate delegate = new TestingPassThroughPersistenceDelegate(null);
		Object retrieve = handler.retrieve(mapping.getField("fieldWithDefaultSettings"), delegate);

		assertThat(retrieve, nullValue());
	}

	@Test
	public void testWithStringInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		PersistenceDelegate delegate = new TestingPassThroughPersistenceDelegate("I'm a String!");
		String retrieve = (String) handler.retrieve(mapping.getField("fieldWithDefaultSettings"), delegate);

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is("I'm a String!"));
	}

	@Test
	public void testIntegerFieldWithStringInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		PersistenceDelegate delegate = new TestingPassThroughPersistenceDelegate("1234");
		Integer retrieve = (Integer) handler.retrieve(mapping.getField("primitiveInt"), delegate);

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is(1234));
	}

	@Test(expected = NumberFormatException.class)
	public void testIntegerFieldWithInvalidStringInput() {
		ItemMap mapping = new ItemMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		PrimitivePropertyHandler handler = new PrimitivePropertyHandler();

		PersistenceDelegate delegate = new TestingPassThroughPersistenceDelegate("BAM");
		Integer retrieve = (Integer) handler.retrieve(mapping.getField("primitiveInt"), delegate);

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is(1234));
	}

}
