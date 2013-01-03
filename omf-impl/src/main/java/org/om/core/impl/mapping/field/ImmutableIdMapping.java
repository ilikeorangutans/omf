package org.om.core.impl.mapping.field;

import org.om.core.api.mapping.field.IdMapping;
import org.om.core.api.mapping.field.Mapping;

public class ImmutableIdMapping implements IdMapping {

	public static final Mapping INSTANCE = new ImmutableIdMapping();

	@Override
	public Class<?> getDeclaredType() {
		return String.class;
	}

	public Class<?> getFieldType() {
		return String.class;
	}

	@Override
	public Class<?> getImplementationType() {
		return String.class;
	}

	public boolean isId() {
		return true;
	}

	public boolean isPrimitiveOrWrappedType() {
		return true;
	}

}
