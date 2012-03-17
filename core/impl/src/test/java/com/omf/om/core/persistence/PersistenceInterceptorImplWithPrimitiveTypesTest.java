package com.omf.om.core.persistence;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.exception.PropertyMissingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.PropertyMapping;

import com.omf.om.core.mapping.EntityWithPrimitiveProperties;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.persistence.delegate.TestingPersistenceContext;
import com.omf.om.core.persistence.delegate.TestingPersistenceDelegate;
import com.omf.om.core.persistence.interceptor.PersistenceInterceptorImpl;

public class PersistenceInterceptorImplWithPrimitiveTypesTest {

	private EntityMapping entityMapping;
	private PersistenceInterceptorImpl interceptor;
	private TestingPersistenceContext persistenceContext;

	@Before
	public void setUp() {
		entityMapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		persistenceContext = new TestingPersistenceContext();
		interceptor = new PersistenceInterceptorImpl(new TestingPersistenceDelegate(entityMapping, persistenceContext));
	}

	@Test
	public void testPrimitiveType() {
		persistenceContext.addProperty("primitiveInt", "2706");

		PropertyMapping propertyMapping = entityMapping.getPropertyByField("primitiveInt");
		int property = (Integer) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(2706));
		// I would love to do that, but java will automatically box the result
		// assertEquals(int.class, property.getClass());
	}

	@Test
	public void testStringType() {
		persistenceContext.addProperty("fieldWithDefaultSettings", "I love Oreos!");

		PropertyMapping propertyMapping = entityMapping.getPropertyByField("fieldWithDefaultSettings");
		String property = (String) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertEquals(String.class, property.getClass());

		assertThat(property, is("I love Oreos!"));
	}

	@Test
	public void testAutoBoxingType() {
		persistenceContext.addProperty("complexFloat", "27.06");

		PropertyMapping propertyMapping = entityMapping.getPropertyByField("complexFloat");
		Float property = (Float) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test(expected = PropertyMissingException.class)
	public void testPropertyMissingThrowingException() {

		PropertyMapping propertyMapping = entityMapping.getPropertyByField("fieldWithAllSettings");
		Float property = (Float) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(Float.valueOf(27.06F)));
	}

	@Test
	public void testPropertyMissingWithDefaultValue() {
		PropertyMapping propertyMapping = entityMapping.getPropertyByField("fieldWithMissingStrategy");
		String property = (String) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is("default value"));
	}

	@Test
	public void testPropertyMissingWithDefaultValueRequiringParsing() {
		PropertyMapping propertyMapping = entityMapping.getPropertyByField("primitiveIntWithDefaultValue");
		int property = (Integer) interceptor.getProperty(propertyMapping);

		assertThat(property, notNullValue());
		assertThat(property, is(2706));
	}

}
