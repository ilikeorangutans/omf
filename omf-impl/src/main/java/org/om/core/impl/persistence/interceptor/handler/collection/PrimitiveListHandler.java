package org.om.core.impl.persistence.interceptor.handler.collection;

import java.util.Collection;
import java.util.Collections;

import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.interceptor.handler.collection.wrapper.PrimitiveListWrapper;

public class PrimitiveListHandler extends AbstractCollectionHandler {
   public PrimitiveListHandler(Session session) {
      super(session);
   }

   @Override
   public Object createCollectionWrapper(MappedField field, CollectionResult result) {
      return new PrimitiveListWrapper(result.getValue());
   }

   @Override
   public Collection<?> createEmptyCollection(MappedField field) {
      return Collections.EMPTY_LIST;
   }
}
