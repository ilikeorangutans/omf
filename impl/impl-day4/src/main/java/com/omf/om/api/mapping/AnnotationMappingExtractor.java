package com.omf.om.api.mapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.omf.om.annotation.Entity;

/**
 * 
 * @author Jakob Külzer
 * 
 */
public class AnnotationMappingExtractor implements MappingExtractor {

	public EntityMapping extract(Class<?> clazz) {
		if (clazz == null)
			throw new NullPointerException("Cannot extract mapping from null.");

		Annotation[] annotations = clazz.getAnnotations();

		if (annotations.length == 0)
			throw new IllegalArgumentException(clazz
					+ " lacks the @Entity annotation.");

		Entity entity = null;
		for (Annotation a : annotations) {
			if (a instanceof Entity) {
				entity = (Entity) a;
			}
		}

		if (entity == null)
			throw new IllegalArgumentException(clazz
					+ " lacks the @Entity annotation.");

		List<AtomMapping> atomMappings = extractAtomMappings(clazz);

		return new ImmutableEntityMappingImpl(clazz, atomMappings);
	}

	private ArrayList<AtomMapping> extractAtomMappings(
			Class<? extends Object> clazz) {
		ArrayList<AtomMapping> result = new ArrayList<AtomMapping>();
		AtomMappingExtractor extractor = new AtomMappingExtractorImpl();

		for (Field f : clazz.getDeclaredFields()) {
			AtomMapping atomMapping = extractor.extract(f);
			if (atomMapping != null)
				result.add(atomMapping);
		}

		return result;
	}
}
