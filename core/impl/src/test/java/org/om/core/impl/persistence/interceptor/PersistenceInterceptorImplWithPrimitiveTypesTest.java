package org.om.core.impl.persistence.interceptor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.exception.PropertyMissingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.persistence.delegate.TestingPersistenceContext;
import org.om.core.impl.persistence.delegate.TestingPersistenceDelegate;
import org.om.core.impl.persistence.interceptor.handler.ItemHandlerFactoryImpl;

public class PersistenceInterceptorImplWithPrimitiveTypesTest {

	private EntityMapping entityMapping;
	private PersistenceInterceptorImpl interceptor;
	private TestingPersistenceContext persistenceContext;

	@Before
	public void setUp() {
		entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		persistenceContext = new TestingPersistenceContext();
		interceptor = new PersistenceInterceptorImpl(null, new ItemHandlerFactoryImpl(), new TestingPersistenceDelegate(entityMapping, persistenceContext));
	}

	@Test
	public void testPrimitiveType() {
		persistenceContext.addProperty("primitiveInt", "2706");

		PropertyMapping propertyMapping = (PropertyMapping) entityMapping.getMappingByField("primitiveInt");
		int property = (Integer) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(2706));
		// I would love to do that, but java will automatically box the result
		// assertEquals(int.class, property.getClass());
	}

	@Test
	public void testStringType() {
		persistenceContext.addProperty("fieldWithDefaultSettings", "I love Oreos!");

		PropertyMapping propertyMapping = (PropertyMapping) entityMapping.getMappingByField("fieldWithDefaultSettings");
		String property = (String) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertEquals(String.class, property.getClass());

		assertThat(property, is("I love Oreos!"));
	}

	@Test
	public void testAutoBoxingType() {
		persistenceContext.addProperty("complexFloat", "27.06");

		PropertyMapping propertyMapping = (PropertyMapping) entityMapping.getMappingByField("complexFloat");
		Float property = (Float) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test(expected = PropertyMissingException.class)
	public void testPropertyMissingThrowingException() {

		PropertyMapping propertyMapping = (PropertyMapping) entityMapping.getMappingByField("fieldWithAllSettings");
		Float property = (Float) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test
	public void testPropertyMissingWithDefaultValue() {
		PropertyMapping propertyMapping = (PropertyMapping) entityMapping.getMappingByField("fieldWithMissingStrategy");
		String property = (String) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is("default value"));
	}

	@Test
	public void testPropertyMissingWithDefaultValueRequiringParsing() {
		PropertyMapping propertyMapping = (PropertyMapping) entityMapping.getMappingByField("primitiveIntWithDefaultValue");
		int property = (Integer) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(2706));
	}

}
