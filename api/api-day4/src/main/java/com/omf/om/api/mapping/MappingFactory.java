package com.omf.om.api.mapping;

public interface MappingFactory {

	EntityMapping getMapping(Class<?> clazz);

}
