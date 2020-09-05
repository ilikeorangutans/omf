package org.om.core.impl.persistence.interceptor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.om.core.api.mapping.*;
import org.om.core.impl.mapping.extractor.*;
import org.om.core.impl.persistence.delegate.*;
import org.om.core.impl.persistence.interceptor.handler.*;
import org.om.core.impl.test.*;

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
	public void testAutoBoxingType() {
		persistenceContext.addProperty("complexFloat", "27.06");
		final MappedField mappedField = entityMapping.getByFieldName("complexFloat");
		final Float property = (Float) interceptor.get(mappedField);
		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test
	public void testPrimitiveType() {
		persistenceContext.addProperty("primitiveInt", "2706");
		final MappedField mappedField = entityMapping.getByFieldName("primitiveInt");
		final int property = (Integer) interceptor.get(mappedField);
		assertThat(property, notNullValue());
		assertThat(property, is(2706));
		// I would love to do that, but java will automatically box the result
		// assertEquals(int.class, property.getClass());
	}

	@Test(expected = RuntimeException.class)
	public void testPropertyMissingThrowingException() {
		final MappedField mappedField = entityMapping.getByFieldName("fieldWithAllSettings");
		final Float property = (Float) interceptor.get(mappedField);
		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test
	public void testPropertyMissingWithDefaultValue() {
		final MappedField mappedField = entityMapping.getByFieldName("fieldWithMissingStrategy");
		final String property = (String) interceptor.get(mappedField);
		assertThat(property, notNullValue());
		assertThat(property, is("default value"));
	}

	@Test
	public void testPropertyMissingWithDefaultValueRequiringParsing() {
		final MappedField mappedField = entityMapping.getByFieldName("primitiveIntWithDefaultValue");
		final int property = (Integer) interceptor.get(mappedField);
		assertThat(property, notNullValue());
		assertThat(property, is(2706));
	}

	@Test
	public void testStringType() {
		persistenceContext.addProperty("fieldWithDefaultSettings", "I love Oreos!");
		final MappedField mappedField = entityMapping.getByFieldName("fieldWithDefaultSettings");
		final String property = (String) interceptor.get(mappedField);
		assertThat(property, notNullValue());
		assertEquals(String.class, property.getClass());
		assertThat(property, is("I love Oreos!"));
	}
}
