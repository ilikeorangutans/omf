package org.om.core.impl.mapping.field;

import org.om.core.api.annotation.LookupMode;
import org.om.core.api.mapping.field.ReferenceMapping;

public class ImmutableReferenceMapping implements ReferenceMapping {

	private final LookupMode lookupMode;
	private final String path;

	public ImmutableReferenceMapping(Class<?> fieldType, String path) {
		this(fieldType, path, LookupMode.Reference);
	}

	public ImmutableReferenceMapping(Class<?> fieldType, String path, LookupMode lookupMode) {
		this.path = path;
		this.lookupMode = lookupMode;
	}

	@Override
	public LookupMode getLookupMode() {
		return lookupMode;
	}

	public String getPath() {
		return path;
	}

	public boolean isId() {
		return false;
	}

	public boolean isPrimitiveOrWrappedType() {
		return false;
	}

	@Override
	public String toString() {
		return "ImmutableReferenceMapping [path=" + path + "]";
	}

}
