package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.*;

import org.om.core.api.mapping.*;
import org.om.core.api.persistence.result.*;
import org.om.core.api.session.*;
import org.om.core.impl.persistence.interceptor.handler.collection.wrapper.*;

public class PrimitiveListHandler extends AbstractCollectionHandler {
	public PrimitiveListHandler(Session session) {
		super(session);
	}

	@Override
	public Object createCollectionWrapper(MappedField field, CollectionResult result) {
		return new PrimitiveListWrapper(result.getValue());
	}

	@Override
	public Collection<?> createEmptyCollection(MappedField field) {
		return Collections.EMPTY_LIST;
	}
}
