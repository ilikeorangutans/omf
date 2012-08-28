package org.om.core.impl.persistence.interceptor.handler.collection.wrapper;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.session.Session;

public class ReferenceHandlingIterator<T> implements Iterator<T> {

	private final CollectionMapping collectionMapping;
	private final Iterator<?> iterator;
	private final Session session;

	public ReferenceHandlingIterator(Session session, CollectionMapping collectionMapping, Iterator<?> iterator) {
		this.session = session;
		this.collectionMapping = collectionMapping;
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();

		Object id = iterator.next();

		return (T) session.get(collectionMapping.getTargetType(), id);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
