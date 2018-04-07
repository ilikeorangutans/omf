package org.om.core.impl.persistence.interceptor.handler.collection.wrapper;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.om.core.api.session.Session;

public class ReferenceHandlingIterator<T> implements Iterator<T> {
   private final Class<?> implementationType;
   private final Iterator<?> iterator;
   private final Session session;

   public ReferenceHandlingIterator(Session session, Class<?> implementationType, Iterator<?> iterator) {
      this.session = session;
      this.implementationType = implementationType;
      this.iterator = iterator;
   }

   @Override
   public boolean hasNext() {
      return iterator.hasNext();
   }

   @Override
   public T next() {
      if (!hasNext()) {
         throw new NoSuchElementException();
      }
      final Object id = iterator.next();
      // TODO: This needs to take into account the actual mappings. As of now,
      // if the referenced id cannot be resolved, this will crash with a
      // PathNotFoundException, ignoring any mappings regarding not found
      // behaviour.
      return (T) session.get(implementationType, id);
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException();
   }
}
