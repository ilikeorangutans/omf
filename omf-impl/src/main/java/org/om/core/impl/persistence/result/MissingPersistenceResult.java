package org.om.core.impl.persistence.result;

import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.api.persistence.result.PersistenceResult;

public class MissingPersistenceResult {
   public static PersistenceResult createMissing(MappedField mappedField) {
      final MissingStrategy strategy = mappedField.getMissingStrategy();
      final boolean hasDefaultOrNullReturn = (strategy == MissingStrategy.DefaultValue) || (strategy == MissingStrategy.ReturnNull);
      if (hasDefaultOrNullReturn) {
         Object value = null;
         if (strategy == MissingStrategy.DefaultValue) {
            value = ((PropertyMapping) mappedField.getMapping()).getDefaultValue();
         }
         return new ImmutablePersistenceResult(value);
      }
      return new ExceptionThrowingPersistenceResult(mappedField.getMissingException());
   }
}
