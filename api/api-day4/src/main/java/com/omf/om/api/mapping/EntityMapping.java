package com.omf.om.api.mapping;

import java.util.List;

public interface EntityMapping {

	public Class<?> getEntityClass();

	public List<AtomMapping> getAtomMappings();

}
