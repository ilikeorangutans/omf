package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.extractor.FieldMappingExtractor;
import org.om.core.impl.test.EntityWithCollections;
import org.om.core.impl.test.EntityWithPrimitiveProperties;

public class ItemMappingExtractorCollectionTest {

	private FieldMappingExtractor extractor = new FieldMappingExtractorImpl();

	@Test
	public void testStringCollection() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithStrings"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());
		assertThat(mapping, instanceOf(CollectionMapping.class));

		assertEquals(String.class, mapping.getTargetType());
	}

	@Test
	public void testIntegerCollection() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithIntegers"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());
		assertThat(mapping, instanceOf(CollectionMapping.class));

		assertEquals(Integer.class, mapping.getTargetType());
	}

	@Test
	public void testReferenceCollection() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("collectionWithReferenceTypes"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());
		assertThat(mapping, instanceOf(CollectionMapping.class));

		assertEquals(EntityWithPrimitiveProperties.class, mapping.getTargetType());
		assertThat(mapping.getLocation(), is("collectionWithReferenceTypes"));
	}

	@Test
	public void testMap() throws Exception {

		MappedField mappedField = extractor.extract(EntityWithCollections.class.getDeclaredField("map"));

		assertThat(mappedField, notNullValue());
		CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		assertThat(mapping, notNullValue());

		assertEquals(EntityWithPrimitiveProperties.class, mapping.getTargetType());
		assertThat(mapping.getLocation(), is("map"));
	}

}
