package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.interceptor.handler.ItemHandlerFactory;
import org.om.core.api.session.Session;

public class ItemHandlerFactoryImpl implements ItemHandlerFactory {

	public ItemHandler get(Session session, Mapping mapping) {

		if (mapping.isPrimitiveOrWrappedType())
			return new PrimitiveHandler();

		if (mapping instanceof CollectionMapping)
			return new CollectionHandler(session);

		return new ReferenceHandler(session);
	}

}
