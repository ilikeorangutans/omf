package org.om.core.impl.persistence.interceptor.handler.map;

import java.util.Collections;

import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.exception.MissingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.result.MapResult;

public class PrimitiveMapHandler implements ItemHandler {
   @Override
   public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
      final CollectionMapping mapping = (CollectionMapping) mappedField.getMapping();
      final MapResult mapResult = adapter.getMapResult(mapping);
      if (!mapResult.hasResult()) {
         // TODO: I've copied this code over from AbstractCollectionHandler.
         // This needs to be refactored.
         if (mappedField.getMissingStrategy() == MissingStrategy.ThrowException) {
            // TODO: Add proper exception logic
            throw new MissingException("TODO: throw proper exception. ");
         } else {
            // TODO: In case of read/write we need to return a proxy.
            return Collections.EMPTY_MAP;
         }
      }
      return new PrimitiveValueMapWrapper(mapResult.getValue());
   }
}
