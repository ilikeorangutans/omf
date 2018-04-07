package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;

public class IdHandler implements ItemHandler {
   @Override
   public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
      return adapter.getId();
   }
}
