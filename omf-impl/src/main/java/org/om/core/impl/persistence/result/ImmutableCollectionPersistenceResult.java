package org.om.core.impl.persistence.result;

import java.util.*;

import org.om.core.api.persistence.result.*;

public class ImmutableCollectionPersistenceResult extends ImmutablePersistenceResult implements CollectionResult {
	public ImmutableCollectionPersistenceResult(Collection<?> result) {
		super(result);
	}

	@Override
	public Collection<?> getValue() {
		return (Collection<?>) super.getValue();
	}
}
