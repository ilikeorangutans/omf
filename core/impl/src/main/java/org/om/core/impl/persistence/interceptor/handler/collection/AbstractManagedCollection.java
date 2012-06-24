package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractManagedCollection<E> implements Collection<E> {

	public boolean add(E arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean contains(Object obj) {

		for (Object o : this) {
			if (o == null && obj == null)
				return true;

			if (o != null && obj != null && o.equals(obj))
				return true;
		}

		return false;
	}

	public boolean containsAll(Collection<?> collection) {

		for (Object o : collection) {
			if (!contains(o))
				return false;
		}

		return true;
	}

	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	public Object[] toArray() {

		return null;
	}

	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
