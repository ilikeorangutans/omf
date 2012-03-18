package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.handler.PropertyHandler;
import org.om.core.api.persistence.interceptor.handler.PropertyHandlerFactory;

public class PropertyHandlerFactoryImpl implements PropertyHandlerFactory {

	public PropertyHandler get(PropertyMapping mapping) {

		if (mapping.isPrimitiveOrWrappedType())
			return new PrimitivePropertyHandler();

		return new ReferencePropertyHandler();
	}

}
