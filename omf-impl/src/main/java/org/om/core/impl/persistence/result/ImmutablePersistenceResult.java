package org.om.core.impl.persistence.result;

import org.om.core.api.persistence.result.PersistenceResult;

/**
 * Default implementation for results that actually contain data.
 *
 * @author Jakob Külzer
 */
public class ImmutablePersistenceResult implements PersistenceResult {
   private final Object result;

   public ImmutablePersistenceResult(Object result) {
      this.result = result;
   }

   @Override
   public Object getValue() {
      return result;
   }

   @Override
   public boolean hasResult() {
      return true;
   }

   @Override
   public String toString() {
      return "ImmutablePersistenceResult [result=" + result + "]";
   }
}
