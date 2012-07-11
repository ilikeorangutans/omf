package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A list wrapper that contains only primitive types.
 * 
 * @author Jakob Külzer
 * 
 * @param <T>
 */
public class PrimitiveListWrapper<T> implements List<T> {

	private final Collection<?> collection;

	public PrimitiveListWrapper(Collection<?> collection) {
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
		if (index < 0 || index >= collection.size())
			throw new IndexOutOfBoundsException(index + " is out of bounds (0 - " + (collection.size() - 1) + ")");

		int i = 0;
		for (Object o : collection) {
			if (i == index)
				return (T) o;
			i++;
		}

		throw new IllegalStateException("This cannot happen.");
	}

	public int indexOf(Object o) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public boolean isEmpty() {
		return collection.isEmpty();
	}

	public Iterator<T> iterator() {
		return (Iterator<T>) collection.iterator();
	}

	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public ListIterator<T> listIterator() {
		return (ListIterator<T>) collection.iterator();
	}

	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException("Not yet implemented.");
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
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public int size() {
		return collection.size();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public Object[] toArray() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

}
