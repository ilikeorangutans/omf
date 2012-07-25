package org.om.core.api.persistence.result;

import java.util.Collection;

public interface CollectionResult extends PersistenceResult {

	public Collection<?> getResult();

}
