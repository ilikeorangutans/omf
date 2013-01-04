package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.om.core.api.annotation.MapKeyStrategy;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.extractor.FieldMappingExtractor;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.impl.test.EntityImplementingInterface;
import org.om.core.impl.test.EntityWithCollections;
import org.om.core.impl.test.EntityWithPrimitiveProperties;
import org.om.core.impl.test.MappedFieldBuilder;
import org.om.core.impl.test.MyInterface;

public class FieldMappingExtractorCollectionTest {

	private FieldMappingExtractor extractor = new FieldMappingExtractorImpl();

	@Test
	public void testStringCollection() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithStrings"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());
		assertThat(mapping, instanceOf(CollectionMapping.class));

		assertEquals(String.class, mapping.getDeclaredType());
		assertEquals(String.class, mapping.getImplementationType());
		assertEquals(List.class, mapping.getCollectionType());
	}

	@Test
	public void testIntegerCollection() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithIntegers"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());
		assertThat(mapping, instanceOf(CollectionMapping.class));

		assertEquals(Integer.class, mapping.getImplementationType());
	}

	@Test
	public void testReferenceCollection() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithReferenceTypes"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());
		assertThat(mapping, instanceOf(CollectionMapping.class));

		assertEquals(EntityWithPrimitiveProperties.class, mapping.getImplementationType());
		assertThat(mapping.getLocation(), is("collectionWithReferenceTypes"));
	}

	@Test
	public void testMap() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("map"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());

		assertEquals(EntityWithPrimitiveProperties.class, mapping.getImplementationType());
		assertThat(mapping.getLocation(), is("map"));
		assertThat(mapping.getMapKeyStrategy(), is(MapKeyStrategy.Name));
	}

	@Test
	public void testExtractMappingForCollectionWithDefaultImplType() throws Exception {
		MappedField field = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithStrings"));
		Mapping mapping = field.getMapping();

		assertEquals(mapping.getDeclaredType(), mapping.getImplementationType());
	}

	@Test
	public void testExtractMappingForCollectionWithDifferingTargetAndImplType() throws Exception {
		MappedField field = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithDifferentTargetAndImplType"));
		Mapping mapping = field.getMapping();

		assertNotSame(mapping.getDeclaredType(), mapping.getImplementationType());
		assertEquals(MyInterface.class, mapping.getDeclaredType());
		assertEquals(EntityImplementingInterface.class, mapping.getImplementationType());
	}

	@Test(expected = MappingException.class)
	public void testExtractingMappingWithIncompatibleTargetAndImplementationTypes() {
		new MappedFieldBuilder().withName("foobar").withType(List.class).withCollectionMapping(List.class, String.class, EntityWithCollections.class, "foobar")
				.create();
	}
}
