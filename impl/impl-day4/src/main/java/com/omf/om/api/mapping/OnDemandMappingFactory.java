package com.omf.om.api.mapping;

/**
 * 
 * @author Jakob Külzer
 * 
 */
public class OnDemandMappingFactory implements MappingFactory {

	private final MappingExtractor extractor;

	public OnDemandMappingFactory() {
		extractor = new AnnotationMappingExtractor();
	}

	public EntityMapping getMapping(Class<?> clazz) {
		return extractor.extract(clazz);
	}

}
