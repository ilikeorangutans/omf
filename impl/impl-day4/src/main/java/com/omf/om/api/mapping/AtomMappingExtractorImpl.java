package com.omf.om.api.mapping;

import java.lang.reflect.Field;

import com.omf.om.annotation.Atom;

public class AtomMappingExtractorImpl implements AtomMappingExtractor {

	public AtomMapping extract(Field f) {
		Atom annotation = f.getAnnotation(Atom.class);
		if (annotation == null)
			return null;

		String fieldName = f.getName();
		String atomName = "".equals(annotation.name()) ? fieldName : annotation
				.name();
		String container = annotation.container();

		return new ImmutableAtomMappingImpl(fieldName, atomName, container);
	}
}
