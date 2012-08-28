package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.test.EntityWithPrimitiveProperties;

public class PrimitivePropertyHandlerTest {

	private PrimitiveHandler handler = new PrimitiveHandler();

	@Test
	public void testNullInput() {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		MappedField field = entityMapping.getByFieldName("fieldWithDefaultSettings");
		assertThat(field, notNullValue());

		PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter(null);
		Object retrieve = handler.retrieve(field, delegate);

		assertThat(retrieve, nullValue());
	}

	@Test
	public void testWithStringInput() {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		MappedField field = entityMapping.getByFieldName("fieldWithDefaultSettings");

		PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter("I'm a String!");
		String retrieve = (String) handler.retrieve(field, delegate);

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is("I'm a String!"));
	}

	@Test
	public void testIntegerFieldWithStringInput() {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		MappedField field = entityMapping.getByFieldName("primitiveInt");

		PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter("1234");
		Integer retrieve = (Integer) handler.retrieve(field, delegate);

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is(1234));
	}

	@Test(expected = NumberFormatException.class)
	public void testIntegerFieldWithInvalidStringInput() {
		EntityMapping entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		MappedField field = entityMapping.getByFieldName("primitiveInt");

		PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter("BAM");
		Integer retrieve = (Integer) handler.retrieve(field, delegate);

		assertThat(retrieve, notNullValue());
		assertThat(retrieve, is(1234));
	}

}
