package org.om.core.impl.mapping.field;

import org.om.core.api.mapping.field.ReferenceMapping;

public class ImmutableReferenceMapping implements ReferenceMapping {

	private final String path;

	public ImmutableReferenceMapping(Class<?> fieldType, String path) {
		this.path = path;
	}

	public boolean isId() {
		return false;
	}

	public boolean isPrimitiveOrWrappedType() {
		return false;
	}

	public String getPath() {
		return path;
	}

}
