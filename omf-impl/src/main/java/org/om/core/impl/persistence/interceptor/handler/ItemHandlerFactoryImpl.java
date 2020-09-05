package org.om.core.impl.persistence.interceptor.handler;

import java.util.*;

import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.interceptor.handler.*;
import org.om.core.api.session.*;
import org.om.core.impl.persistence.interceptor.handler.collection.*;
import org.om.core.impl.persistence.interceptor.handler.map.*;

public class ItemHandlerFactoryImpl implements ItemHandlerFactory {
	private static final IdHandler ID_HANDLER = new IdHandler();

	@Override
	public ItemHandler get(Session session, MappedField field) {
		final Mapping mapping = field.getMapping();
		if (mapping instanceof IdMapping) {
			return ID_HANDLER;
		}
		if (mapping.isPrimitiveOrWrappedType()) {
			return new PrimitiveHandler();
		}
		if (mapping instanceof CollectionMapping) {
			final Class<?> fieldType = field.getType();
			final Class<?> implementationType = mapping.getImplementationType();
			final boolean primitive = mapping.isPrimitiveOrWrappedType() || String.class.equals(implementationType);
			if (List.class.equals(fieldType)) {
				if (primitive) {
					return new PrimitiveListHandler(session);
				} else {
					return new ReferenceListHandler(session);
				}
			} else if (Map.class.equals(fieldType)) {
				return new PrimitiveMapHandler();
			}
			throw new IllegalArgumentException("Don't know how to create ItemHandler for " + field);
		}
		return new ReferenceHandler(session);
	}
}
