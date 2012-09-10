package org.om.core.impl.persistence.interceptor.handler;

import java.util.List;
import java.util.Map;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.IdMapping;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.interceptor.handler.ItemHandlerFactory;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.interceptor.handler.collection.PrimitiveListHandler;
import org.om.core.impl.persistence.interceptor.handler.collection.ReferenceListHandler;
import org.om.core.impl.persistence.interceptor.handler.map.PrimitiveMapHandler;
import org.om.core.impl.util.ClassUtils;

public class ItemHandlerFactoryImpl implements ItemHandlerFactory {

	private static final IdHandler ID_HANDLER = new IdHandler();

	public ItemHandler get(Session session, MappedField field) {

		final Mapping mapping = field.getMapping();

		if (mapping instanceof IdMapping) {
			return ID_HANDLER;
		}

		if (mapping.isPrimitiveOrWrappedType())
			return new PrimitiveHandler();

		if (mapping instanceof CollectionMapping) {
			final Class<?> fieldType = field.getType();
			final CollectionMapping collectionMapping = (CollectionMapping) mapping;
			final Class<?> targetType = collectionMapping.getTargetType();
			final boolean primitive = ClassUtils.isPrimitiveOrAutoboxed(targetType) || String.class.equals(targetType);

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
