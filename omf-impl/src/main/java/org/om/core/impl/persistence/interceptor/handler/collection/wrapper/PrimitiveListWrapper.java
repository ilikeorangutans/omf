package org.om.core.impl.persistence.interceptor.handler.collection.wrapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A list wrapper that contains only primitive types.
 *
 * @author Jakob Külzer
 * @param <T>
 */
public class PrimitiveListWrapper<T> implements List<T> {
   private final Collection<?> collection;

   public PrimitiveListWrapper(Collection<?> collection) {
      this.collection = collection;
   }

   @Override
   public void add(int index, T element) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean add(T e) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean addAll(Collection<? extends T> c) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean addAll(int index, Collection<? extends T> c) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean contains(Object o) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean containsAll(Collection<?> c) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public T get(int index) {
      if ((index < 0) || (index >= collection.size())) {
         throw new IndexOutOfBoundsException(index + " is out of bounds (0 - " + (collection.size() - 1) + ")");
      }
      int i = 0;
      for (final Object o : collection) {
         if (i == index) {
            return (T) o;
         }
         i++;
      }
      throw new IllegalStateException("This cannot happen.");
   }

   @Override
   public int indexOf(Object o) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean isEmpty() {
      return collection.isEmpty();
   }

   @Override
   public Iterator<T> iterator() {
      return (Iterator<T>) collection.iterator();
   }

   @Override
   public int lastIndexOf(Object o) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public ListIterator<T> listIterator() {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public ListIterator<T> listIterator(int index) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public T remove(int index) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean remove(Object o) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean removeAll(Collection<?> c) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public boolean retainAll(Collection<?> c) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public T set(int index, T element) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public int size() {
      return collection.size();
   }

   @Override
   public List<T> subList(int fromIndex, int toIndex) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public Object[] toArray() {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   @Override
   public <T> T[] toArray(T[] a) {
      throw new UnsupportedOperationException("Not yet implemented.");
   }
}
