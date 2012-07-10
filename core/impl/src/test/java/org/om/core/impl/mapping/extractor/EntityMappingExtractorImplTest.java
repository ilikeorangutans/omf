package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Fields;
import org.om.core.impl.mapping.EntityWithInvalidCollectionMapping;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.EntityWithoutProperties;

@SuppressWarnings("unused")
public class EntityMappingExtractorImplTest {

	private EntityMappingExtractorImpl extractor = new EntityMappingExtractorImpl();

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = MappingException.class)
	public void testEntityWithoutAnnotation() throws Exception {

		final EntityMapping mapping = extractor.extract(getClass());
	}

	@Test(expected = MappingException.class)
	public void testEntityWithoutIdProperty() {
		EntityMapping mapping = extractor.extract(EntityWithoutProperties.class);
	}

	@Test
	public void testEntityWithBasicProperties() {
		final EntityMapping mapping = extractor.extract(EntityWithPrimitiveProperties.class);

		assertThat(mapping, notNullValue());
		assertEquals(EntityWithPrimitiveProperties.class, mapping.getTypeClass());
		assertThat(mapping.getMappedFields().getSize(), is(EntityWithPrimitiveProperties.NUMBER_OF_FIELDS));
	}

	@Test
	public void testSimpleTypeWithoutProperties() {
		Fields extract = extractor.extractFields(EntityWithoutProperties.class);
		assertThat("Non-entites should not generate any mapped fields.", extract.isEmpty(), is(true));
	}

	@Test
	public void testSimpleTypeWithBasicProperties() {
		Fields extract = extractor.extractFields(EntityWithPrimitiveProperties.class);

		assertThat(extract.isEmpty(), is(false));
		assertThat(extract.getSize(), is(EntityWithPrimitiveProperties.NUMBER_OF_FIELDS));
	}

	@Test(expected = MappingException.class)
	public void testExtractWithInvalidCollectionMapping() {
		extractor.extract(EntityWithInvalidCollectionMapping.class);
	}
}
