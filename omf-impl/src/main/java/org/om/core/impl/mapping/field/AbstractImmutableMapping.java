package org.om.core.impl.mapping.field;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.field.*;
import org.om.core.impl.util.*;

public class AbstractImmutableMapping implements Mapping {
	private final Class<?> declaredType;
	private final Class<?> implementationType;
	private final boolean simpleType;

	public AbstractImmutableMapping(Class<?> declaredType, Class<?> implementationType) {
		this.declaredType = declaredType;
		this.implementationType = implementationType;
		simpleType = String.class.equals(declaredType) || ClassUtils.isPrimitiveOrAutoboxed(declaredType);
		if (!declaredType.isAssignableFrom(implementationType)) {
			throw new MappingException("Invalid mapping. Implementation type " + implementationType.getName() + " is not a subtype of declared type " + declaredType.getName());
		}
	}

	@Override
	public Class<?> getDeclaredType() {
		return declaredType;
	}

	@Override
	public Class<?> getImplementationType() {
		return implementationType;
	}

	@Override
	public boolean isId() {
		return false;
	}

	@Override
	public boolean isPrimitiveOrWrappedType() {
		return simpleType;
	}
}
