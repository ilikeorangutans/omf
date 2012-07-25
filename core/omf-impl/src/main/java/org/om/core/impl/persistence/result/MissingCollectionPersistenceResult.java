package org.om.core.impl.persistence.result;

import java.util.Collection;

import org.om.core.api.exception.MissingException;
import org.om.core.api.persistence.result.CollectionResult;

public class MissingCollectionPersistenceResult implements CollectionResult {

	@Override
	public Collection<?> getResult() {
		throw new MissingException("Collection could not be populated.");
	}

	@Override
	public boolean hasResult() {
		return false;
	}

}
