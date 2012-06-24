package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.session.Session;

public class ListWrapper<T> implements List<T> {

	private final Session session;
	private final Collection<?> collection;
	private final CollectionMapping collectionMapping;

	public ListWrapper(Session session, CollectionMapping collectionMapping, Collection<?> collection) {
		this.session = session;
		this.collectionMapping = collectionMapping;
		this.collection = collection;
	}

	public boolean add(T e) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public void add(int index, T element) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public void clear() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public T get(int index) {

		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		return collection.isEmpty();
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public T remove(int index) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
