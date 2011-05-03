package com.omf.om.api.mapping;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class AtomMappingExtractorImplTest {

	private AtomMappingExtractor extractor;

	@Before
	public void setUp() throws Exception {
		extractor = new AtomMappingExtractorImpl();
	}

	@Test
	public void testExtract() throws Exception {
		Field field = SimpleEntity.class.getDeclaredField("field");

		AtomMapping mapping = extractor.extract(field);
		assertNotNull(mapping);
		assertEquals("field", mapping.getAtomName());
		assertEquals("field", mapping.getFieldName());
		assertEquals("", mapping.getContainerName());
	}

	@Test
	public void testExtractMore() throws Exception {
		Field field = SimpleEntity.class
				.getDeclaredField("fieldWithDifferentAtom");

		AtomMapping mapping = extractor.extract(field);
		assertNotNull(mapping);
		assertEquals("differentAtom", mapping.getAtomName());
		assertEquals("fieldWithDifferentAtom", mapping.getFieldName());
		assertEquals("", mapping.getContainerName());

	}
}
