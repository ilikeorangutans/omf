package org.om.core.api.persistence.result;

import java.util.*;

public interface MapResult extends PersistenceResult {
	@Override
	Map<?, ?> getValue();
}
