package com.omf.om.api.mapping;

import java.lang.reflect.Field;

public interface AtomMappingExtractor {

	AtomMapping extract(Field f);

}
