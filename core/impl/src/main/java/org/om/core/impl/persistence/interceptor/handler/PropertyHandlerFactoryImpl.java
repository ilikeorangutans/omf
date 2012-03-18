package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.handler.PropertyHandler;
import org.om.core.api.persistence.interceptor.handler.PropertyHandlerFactory;
import org.om.core.api.session.Session;

public class PropertyHandlerFactoryImpl implements PropertyHandlerFactory {

	public PropertyHandler get(Session session, PropertyMapping mapping) {

		if (mapping.isPrimitiveOrWrappedType())
			return new PrimitivePropertyHandler();

		// If the property doesn't map a primitive type it has to be a reference
		// or collection type.

		return new ReferencePropertyHandler(session);
	}

}
