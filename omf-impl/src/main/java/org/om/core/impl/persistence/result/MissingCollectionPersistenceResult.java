package org.om.core.impl.persistence.result;

import java.util.*;

import org.om.core.api.exception.*;
import org.om.core.api.persistence.result.*;

public class MissingCollectionPersistenceResult implements CollectionResult {
	@Override
	public Collection<?> getValue() {
		throw new MissingException("Collection could not be populated.");
	}

	@Override
	public boolean hasResult() {
		return false;
	}
}
