package org.om.core.impl.persistence.interceptor.handler.collection.wrapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.session.Session;

/**
 * List handler that handles reference list types.
 * 
 * @author Jakob Külzer
 * 
 */
public class ReferenceListWrapper<T> implements List<T> {

	private final Collection<?> collection;
	private final CollectionMapping collectionMapping;
	private final Session session;

	public ReferenceListWrapper(Session session, CollectionMapping collectionMapping, Collection<?> result) {
		this.session = session;
		this.collectionMapping = collectionMapping;
		this.collection = result;
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(T e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException(Integer.toString(index));

		int i = 0;
		for (Object o : collection) {
			if (index == i)
				return (T) session.get(collectionMapping.getImplementationType(), o);
			i++;
		}

		throw new IllegalStateException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return new ReferenceHandlingIterator(session, collectionMapping, collection.iterator());
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

}
