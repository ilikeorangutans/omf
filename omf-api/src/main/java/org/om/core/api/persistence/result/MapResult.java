package org.om.core.api.persistence.result;

import java.util.Map;

public interface MapResult extends PersistenceResult {
   @Override
   Map<?, ?> getValue();
}
