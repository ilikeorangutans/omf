package org.om.core.impl.persistence.result;

import org.om.core.api.persistence.result.*;

public final class NoValuePersistenceResult implements PersistenceResult {
	@Override
	public Object getValue() {
		throw new IllegalStateException("No result available.");
	}

	@Override
	public boolean hasResult() {
		return false;
	}
}
