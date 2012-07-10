package org.om.core.impl.persistence.interceptor.handler;

import java.util.List;
import java.util.Set;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.interceptor.handler.collection.ListWrapper;

/**
 * An {@link ItemHandler} that access collections.
 * 
 * @author Jakob Külzer
 * 
 */
public class CollectionHandler implements ItemHandler {

	private final Session session;

	public CollectionHandler(Session session) {
		this.session = session;
	}

	@Override
	public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
		final CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
		final CollectionResult collectionResult = adapter.getCollection(mapping);
		final CollectionMapping collectionMapping = (CollectionMapping) mapping;

		final Class<?> fieldType = mappedField.getType();
		if (fieldType == List.class) {

			return new ListWrapper(session, collectionMapping, collectionResult.getResult());

		} else if (fieldType == Set.class) {
			throw new IllegalStateException("IMPLEMENT ME!!");
		} else {
			throw new MappingException("Collection " + mappedField.getType() + " is of type " + fieldType + fieldType
					+ ". Don't know how to handle this. Make sure it's either java.util.List or java.util.Set.");
		}

	}
}
