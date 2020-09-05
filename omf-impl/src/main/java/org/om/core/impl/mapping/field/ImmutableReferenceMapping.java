package org.om.core.impl.mapping.field;

import org.om.core.api.annotation.*;
import org.om.core.api.mapping.field.*;

public class ImmutableReferenceMapping extends AbstractImmutableMapping implements ReferenceMapping {
	private final LookupMode lookupMode;
	private final String path;

	public ImmutableReferenceMapping(Class<?> declaredType, Class<?> implementationType, String path, LookupMode lookupMode) {
		super(declaredType, implementationType);
		this.path = path;
		this.lookupMode = lookupMode;
	}

	@Override
	public LookupMode getLookupMode() {
		return lookupMode;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public boolean isId() {
		return false;
	}

	@Override
	public boolean isPrimitiveOrWrappedType() {
		return false;
	}

	@Override
	public String toString() {
		return "ImmutableReferenceMapping [path=" + path + "]";
	}
}
