package org.om.core.impl.persistence.interceptor;

import java.util.Collection;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.interceptor.handler.ItemHandlerFactory;
import org.om.core.api.session.Session;

/**
 * Implementation of {@link PersistenceInterceptor}, takes care of calling the
 * appropriate {@link ItemHandler}s and handles missing properties and default
 * values via {@link MissingBehaviorPersistenceDelegateDecorator}.
 * 
 * @author Jakob Külzer
 * 
 */
public class PersistenceInterceptorImpl implements PersistenceInterceptor {

	private final PersistenceAdapter adapter;

	private final ItemHandlerFactory handlerFactory;

	private final Session session;

	public PersistenceInterceptorImpl(Session session, ItemHandlerFactory handlerFactory, PersistenceAdapter adapter) {
		this.session = session;
		this.handlerFactory = handlerFactory;
		// this.delegate = new
		// MissingBehaviorPersistenceDelegateDecorator(delegate);
		this.adapter = adapter;
	}

	public Object get(MappedField mappedField) {
		if (mappedField == null)
			throw new NullPointerException("MappedField is null");

		final ItemHandler handler = handlerFactory.get(session, mappedField.getMapping());
		if (handler == null)
			throw new NullPointerException("No handler for mapped field " + mappedField.getName() + " found.");

		return handler.retrieve(mappedField, adapter);
	}

	public Collection<?> getCollection(CollectionMapping collectionMapping) {
		return null;
	}

}
