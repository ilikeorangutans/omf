package org.om.core.impl.persistence.interceptor.handler;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.interceptor.handler.collection.ListWrapper;

/**
 * An {@link ItemHandler} that access collections.
 * 
 * @author Jakob Külzer
 * 
 */
public class CollectionPropertyHandler implements ItemHandler {

	private final Session session;

	public CollectionPropertyHandler(Session session) {
		this.session = session;
	}

	public Object retrieve(Mapping mapping, PersistenceDelegate delegate) {
		final Collection<?> collection = delegate.getCollection((CollectionMapping) mapping);
		final CollectionMapping collectionMapping = (CollectionMapping) mapping;

		final Class<?> fieldType = mapping.getFieldType();
		if (fieldType == List.class) {

			return new ListWrapper(session, collectionMapping, collection);

		} else if (fieldType == Set.class) {
		} else {
			throw new MappingException("Collection " + mapping.getFieldname() + " is of type " + fieldType + fieldType
					+ ". Don't know how to handle this. Make sure it's either java.util.List or java.util.Set.");
		}

		return null;
	}

}
