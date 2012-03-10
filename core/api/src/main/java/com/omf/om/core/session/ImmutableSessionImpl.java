package com.omf.om.core.session;

import com.omf.om.api.mapping.registry.MappingRegistry;
import com.omf.om.api.session.Session;

public class ImmutableSessionImpl implements Session {

	private final MappingRegistry mappingRegistry;

	public ImmutableSessionImpl(MappingRegistry mappingRegistry) {
		this.mappingRegistry = mappingRegistry;
	}

	public <T> T get(Class<T> clazz, String path) {
		if (clazz == null)
			throw new NullPointerException("Class is null.");

		return null;
	}

}
