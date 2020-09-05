package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.Collection;

import org.om.core.api.annotation.*;
import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.handler.*;
import org.om.core.api.persistence.result.*;
import org.om.core.api.session.*;

/**
 * An {@link ItemHandler} that access collections.
 *
 * @author Jakob Külzer
 */
public abstract class AbstractCollectionHandler implements ItemHandler {
	protected Session session;

	public AbstractCollectionHandler(Session session) {
		this.session = session;
	}

	public abstract Object createCollectionWrapper(MappedField field, CollectionResult result);

	public abstract Collection<?> createEmptyCollection(MappedField field);

	@Override
	public final Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
		final CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		final CollectionResult collectionResult = adapter.getCollection(mapping);
		if (!collectionResult.hasResult()) {
			if (mappedField.getMissingStrategy() == MissingStrategy.ThrowException) {
				// TODO: Add proper exception logic
				throw new MissingException("TODO: throw proper exception");
			} else {
				return createEmptyCollection(mappedField);
			}
		}
		return createCollectionWrapper(mappedField, collectionResult);
	}
}
