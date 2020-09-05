package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.*;

import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.result.*;
import org.om.core.api.session.*;
import org.om.core.impl.persistence.interceptor.handler.collection.wrapper.*;

public class ReferenceListHandler extends AbstractCollectionHandler {
	public ReferenceListHandler(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object createCollectionWrapper(MappedField field, CollectionResult result) {
		final Mapping mapping = field.getMapping();
		return new ReferenceListWrapper(session, (CollectionMapping) field.getMapping(), mapping.getImplementationType(), result.getValue());
	}

	@Override
	public Collection<?> createEmptyCollection(MappedField field) {
		return Collections.EMPTY_LIST;
	}
}
