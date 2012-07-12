package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.field.IdMapping;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.interceptor.handler.ItemHandlerFactory;
import org.om.core.api.session.Session;

public class ItemHandlerFactoryImpl implements ItemHandlerFactory {

	public ItemHandler get(Session session, Mapping mapping) {

		if (mapping.isPrimitiveOrWrappedType())
			return new PrimitiveHandler();

		if (mapping instanceof CollectionMapping)
			return new CollectionHandler(session);

		if (mapping instanceof IdMapping) {
			// TODO: This needs to implemented differently. Ideally we'd return
			// some handler that returns the path of the currently retrieved
			// object.
			throw new UnsupportedOperationException();
		}

		return new ReferenceHandler(session);
	}

}
