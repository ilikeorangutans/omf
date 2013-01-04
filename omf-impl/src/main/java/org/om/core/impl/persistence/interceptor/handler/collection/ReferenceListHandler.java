package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.Collection;
import java.util.Collections;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.Mapping;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.interceptor.handler.collection.wrapper.ReferenceListWrapper;

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
