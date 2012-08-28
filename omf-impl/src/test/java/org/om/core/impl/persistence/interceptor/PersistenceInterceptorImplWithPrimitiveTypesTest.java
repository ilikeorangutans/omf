package org.om.core.impl.persistence.interceptor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.persistence.delegate.TestingPersistenceAdapter;
import org.om.core.impl.persistence.delegate.TestingPersistenceContext;
import org.om.core.impl.persistence.interceptor.handler.ItemHandlerFactoryImpl;
import org.om.core.impl.test.EntityWithPrimitiveProperties;

public class PersistenceInterceptorImplWithPrimitiveTypesTest {

	private EntityMapping entityMapping;
	private PersistenceInterceptorImpl interceptor;
	private TestingPersistenceContext persistenceContext;

	@Before
	public void setUp() {
		entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		persistenceContext = new TestingPersistenceContext();
		interceptor = new PersistenceInterceptorImpl(null, new ItemHandlerFactoryImpl(), new TestingPersistenceAdapter(entityMapping, persistenceContext));
	}

	@Test
	public void testPrimitiveType() {
		persistenceContext.addProperty("primitiveInt", "2706");

		MappedField mappedField = entityMapping.getByFieldName("primitiveInt");
		int property = (Integer) interceptor.get(mappedField);

		assertThat(property, notNullValue());
		assertThat(property, is(2706));
		// I would love to do that, but java will automatically box the result
		// assertEquals(int.class, property.getClass());
	}

	@Test
	public void testStringType() {
		persistenceContext.addProperty("fieldWithDefaultSettings", "I love Oreos!");

		MappedField mappedField = entityMapping.getByFieldName("fieldWithDefaultSettings");
		String property = (String) interceptor.get(mappedField);

		assertThat(property, notNullValue());
		assertEquals(String.class, property.getClass());

		assertThat(property, is("I love Oreos!"));
	}

	@Test
	public void testAutoBoxingType() {
		persistenceContext.addProperty("complexFloat", "27.06");

		MappedField mappedField = entityMapping.getByFieldName("complexFloat");
		Float property = (Float) interceptor.get(mappedField);

		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test(expected = RuntimeException.class)
	public void testPropertyMissingThrowingException() {
		MappedField mappedField = entityMapping.getByFieldName("fieldWithAllSettings");
		Float property = (Float) interceptor.get(mappedField);

		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test
	public void testPropertyMissingWithDefaultValue() {
		MappedField mappedField = entityMapping.getByFieldName("fieldWithMissingStrategy");
		String property = (String) interceptor.get(mappedField);

		assertThat(property, notNullValue());
		assertThat(property, is("default value"));
	}

	@Test
	public void testPropertyMissingWithDefaultValueRequiringParsing() {
		MappedField mappedField = entityMapping.getByFieldName("primitiveIntWithDefaultValue");
		int property = (Integer) interceptor.get(mappedField);

		assertThat(property, notNullValue());
		assertThat(property, is(2706));
	}

}
