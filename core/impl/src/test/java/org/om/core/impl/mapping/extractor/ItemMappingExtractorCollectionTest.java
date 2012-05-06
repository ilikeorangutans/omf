package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.ItemMap;
import org.om.core.impl.mapping.EntityWithCollections;
import org.om.core.impl.mapping.EntityWithInvalidCollectionMapping;

public class ItemMappingExtractorCollectionTest {

	private ItemMappingExtractorImpl extractor;

	@Before
	public void setUp() {
		extractor = new ItemMappingExtractorImpl();
	}

	@Test
	public void test() {

		ItemMap map = extractor.extract(EntityWithCollections.class);

		assertThat(map, notNullValue());
		assertThat(map.getSize(), is(4));
		assertThat(map.hasField("collectionWithStrings"), is(true));

		CollectionMapping mapping = (CollectionMapping) map.getField("collectionWithStrings");
		assertThat(mapping, notNullValue());
		assertEquals(String.class, mapping.getTargetType());
	}

	@Test(expected = MappingException.class)
	public void testWithInvalidCollectionMappings() throws Exception {

		extractor.extract(EntityWithInvalidCollectionMapping.class);

	}

	@Test
	public void testWithInvalidAutboxedCollectionType() throws Exception {

		ItemMap map = extractor.extract(EntityWithCollections.class);

		assertThat(map, notNullValue());
		CollectionMapping mapping = (CollectionMapping) map.getField("collectionWithIntegers");
		assertThat(mapping, notNullValue());
		assertEquals(mapping.getTargetType(), Integer.class);
	}

}
