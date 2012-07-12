package org.om.core.impl.persistence.result;

import org.om.core.api.persistence.result.PersistenceResult;

public final class NoValuePersistenceResult implements PersistenceResult {

	@Override
	public boolean hasResult() {
		return false;
	}

	@Override
	public Object getResult() {
		throw new IllegalStateException("No result available.");
	}

}
