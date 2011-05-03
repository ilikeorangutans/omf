package com.omf.om.api.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class AnnotationMappingExtractorTest {

	private MappingExtractor extractor;

	@Before
	public void setUp() throws Exception {
		extractor = new AnnotationMappingExtractor();
	}

	@Test
	public void testExtract() {
		EntityMapping mapping = extractor.extract(SimpleEntity.class);

		assertNotNull(mapping);
		assertNotNull(mapping.getAtomMappings());
		assertEquals(3, mapping.getAtomMappings().size());

		AtomMapping firstAtom = mapping.getAtomMappings().get(0);
		assertEquals("field", firstAtom.getAtomName());

		AtomMapping secondAtom = mapping.getAtomMappings().get(1);
		assertEquals("differentAtom", secondAtom.getAtomName());
	}
}
